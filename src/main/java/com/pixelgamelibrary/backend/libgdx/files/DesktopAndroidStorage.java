///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: LibGdx Backend.
// Copyright (C) 2024 the original author or authors.
//
// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation, either version 3
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see
// <https://www.gnu.org/licenses/> or write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////
package com.pixelgamelibrary.backend.libgdx.files;

import com.badlogic.gdx.Gdx;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.files.FileType;
import com.pixelgamelibrary.api.files.RegularFileType;
import com.pixelgamelibrary.api.files.Storage;
import com.pixelgamelibrary.api.files.StorageException;
import com.pixelgamelibrary.api.files.StorageType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author robertvokac
 */
public abstract class DesktopAndroidStorage implements Storage {

    private String workingDirectory = "/";
    private final String storageName;

    public DesktopAndroidStorage(String storageName) {
        if (storageName == null || storageName.trim().isEmpty()) {
            var msg = "storageName == null || storageName.trim().isEmpty()";
            Pixel.app().error(msg);
            throw new StorageException(msg);
        }
        this.storageName = storageName;
        com.badlogic.gdx.files.FileHandle rootFileHandle = createLibGdxFileHandle("/");
        if (!rootFileHandle.exists()) {
            rootFileHandle.mkdirs();
        }
    }

    @Override
    public String changeDirectory(String path) {
        com.badlogic.gdx.files.FileHandle fh = createLibGdxFileHandle(path);
        if (!fh.exists()) {
            var msg = "Directory does not exist: " + path;
            Pixel.app().error(msg);
            return msg;
        }
        workingDirectory = path;
        return "";

    }

    com.badlogic.gdx.files.FileHandle createLibGdxFileHandle(String path) {
        if (path.equals("/")) {
            return Gdx.files.local(storageName);
        } else {
            return Gdx.files.local(storageName + "/" + path);
        }

    }

    @Override
    public String createDirectory(String path) {
        var dir = createLibGdxFileHandle(path);
        if (dir.exists()) {
            var msg = "Directory already exists: " + path;
            Pixel.app().warn(msg);
            return msg;
        } else {
            dir.mkdirs();
            return "";
        }
    }

    @Override
    public String printWorkingDirectory() {
        return workingDirectory;
    }

    @Override
    public List<String> list(String path) {
        path = convertToAbsolutePathIfNeeded(path);
        return Arrays.asList(createLibGdxFileHandle(path).list()).stream().map(e -> e.path()).collect(Collectors.toList());
    }

    @Override
    public String touch(String path) {
        path = convertToAbsolutePathIfNeeded(path);
        createLibGdxFileHandle(path).writeString("", false);
        return "";
    }

    @Override
    public boolean remove(String path) {
        path = convertToAbsolutePathIfNeeded(path);
        return createLibGdxFileHandle(path).delete();
    }

    @Override
    public String copy(String source, String target) {

        source = convertToAbsolutePathIfNeeded(source);
        target = convertToAbsolutePathIfNeeded(target);
        createLibGdxFileHandle(source).copyTo(createLibGdxFileHandle(target));
        return "";

    }

    @Override
    public String move(String source, String target) {
        source = convertToAbsolutePathIfNeeded(source);
        target = convertToAbsolutePathIfNeeded(target);
        createLibGdxFileHandle(source).moveTo(createLibGdxFileHandle(target));
        return "";
    }

    @Override
    public String readString(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return createLibGdxFileHandle(path).readString();
    }

    @Override
    public byte[] readBytes(String path) {
        try {
            path = convertToAbsolutePathIfNeeded(path);
            // Use FileHandle's readBytes() method directly, as GWT supports it
            com.badlogic.gdx.files.FileHandle fileHandle = createLibGdxFileHandle(path);
            return fileHandle.readBytes();
        } catch (Exception ex) {
            Pixel.app().error(ex.getMessage());
            throw new StorageException(ex.getMessage());
        }
    }

    @Override
    public String writeString(String path, String text) {

        path = convertToAbsolutePathIfNeeded(path);
        createLibGdxFileHandle(path).writeString(text, false);
        return "";
    }

    @Override
    public String writeBytes(String path, byte[] data) {

        path = convertToAbsolutePathIfNeeded(path);
        createLibGdxFileHandle(path).writeBytes(data, false);
        return "";
    }

    @Override
    public boolean exists(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return createLibGdxFileHandle(path).exists();
    }

    @Override
    public boolean isFile(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return !createLibGdxFileHandle(path).isDirectory();
    }

    @Override
    public boolean isDirectory(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return createLibGdxFileHandle(path).isDirectory();
    }

    @Override
    public String debug() {

        return printFileTree(createLibGdxFileHandle(SLASH));
    }

    public String printFileTree(com.badlogic.gdx.files.FileHandle dir) {
        StringBuilder sb = new StringBuilder();
        printFileTree(dir, "", sb);
        return sb.toString();
    }

    private void printFileTree(com.badlogic.gdx.files.FileHandle file, String indent, StringBuilder sb) {
        // Add the current file or folder
        sb.append(indent).append(file.name()).append("\n");

        if (file.isDirectory()) {
            // If it's a directory, get its contents and recursively process them
            com.badlogic.gdx.files.FileHandle[] children = file.list();
            for (com.badlogic.gdx.files.FileHandle child : children) {
                printFileTree(child, indent + "    ", sb); // Indentation for subfiles
            }
        }
    }

    @Override
    public void flush() {
        //nothing to do
    }

    @Override
    public boolean removeDirectory(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return createLibGdxFileHandle(path).deleteDirectory();
    }

    @Override
    public FileType type(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
    }

    @Override
    public RegularFileType getRegularFileType(String path) {

        path = convertToAbsolutePathIfNeeded(path);
        return isTextFile(createLibGdxFileHandle(path)) ? RegularFileType.TEXT : RegularFileType.BINARY;
    }

    
    private boolean isTextFile(com.badlogic.gdx.files.FileHandle file) {
        String content;
        try {
        content = file.readString();
        } catch(Exception e) {System.out.println(e.getMessage());throw e;}
        return isTextFile(content);
    }

    @Override
    public byte[] backup(String methodName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void restore(String methodName, byte[] data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isReadonly() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StorageType getStorageType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
