///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: LibGDX Backend.
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

import com.pixelgamelibrary.api.utils.AssetsTxt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.files.FileType;
import com.pixelgamelibrary.api.files.RegularFileType;
import com.pixelgamelibrary.api.files.FileSystemType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import com.pixelgamelibrary.api.files.FileSystem;

/**
 *
 * @author robertvokac
 */
public class AssetsLibGDXFileSystem implements FileSystem {

    private boolean isProbablyTeaVM = false;
    @Getter
    private final AssetsTxt assets;
    private String workingDirectory = "/";

    public AssetsLibGDXFileSystem() {
        assets = new AssetsTxt(readAssetsTxt());
    }

    private String readAssetsTxt() {
        
        for(FileHandle fh: Gdx.files.internal(".").list() ) {
        System.out.println("fh=" + fh.path());
    }
        final String ASSETSTXT = "assets.txt";
        final String ASSETS_TXT = "assets_.txt";
        
        FileHandle fileHandle = Gdx.files.internal(ASSETSTXT);
        if(!fileHandle.exists()) {
            fileHandle = Gdx.files.internal(ASSETS_TXT);
            isProbablyTeaVM = true;
        }
        return fileHandle.readString();
    }

    @Override
    public Platform getPlatform() {
        return Pixel.app().getPlatform();
    }

    @Override
    public String changeDirectory(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        if (!assets.containsDirectory(path)) {
            String msg = "There is no such directory in assets: " + path;
            Pixel.app().log(msg);
            return msg;
        }
        workingDirectory = path;
        return "";
    }

    @Override
    public String createDirectory(String argument) {
        throw createUnsupportedOperationException();
    }

    private UnsupportedOperationException createUnsupportedOperationException() throws UnsupportedOperationException {
        return new UnsupportedOperationException("This operation is not supported. Assets are readonly");
    }

    @Override
    public String printWorkingDirectory() {
        return workingDirectory;
    }

    @Override
    public List<String> list(String path) {
        if (path.equals("/")) {
            return assets
                    .listRoot()
                    .stream()
                    .map(s -> "/" + s)
                    .collect(Collectors.toList());
        }
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        final String finalPath = path;
        return assets
                .list(finalPath)
                .stream()
                .map(s -> finalPath + "/" + s)
                .collect(Collectors.toList());
    }

    @Override
    public String touch(String name) {
        throw createUnsupportedOperationException();
    }

    @Override
    public boolean remove(String name) {
        throw createUnsupportedOperationException();
    }

    @Override
    public boolean removeDirectory(String dirname) {
        throw createUnsupportedOperationException();
    }

    @Override
    public String copy(String source, String target) {
        throw createUnsupportedOperationException();
    }

    @Override
    public String move(String source, String target) {
        throw createUnsupportedOperationException();
    }

    @Override
    public String readString(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return createEmbeddedLibGDXFileHandle(path).readString();
    }

    @Override
    public byte[] readBytes(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return createEmbeddedLibGDXFileHandle(path).readBytes();
    }

    @Override
    public String writeString(String name, String text) {
        throw createUnsupportedOperationException();
    }

    @Override
    public String writeBytes(String name, byte[] data) {
        throw createUnsupportedOperationException();
    }

    @Override
    public boolean exists(String path) {

        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return createEmbeddedLibGDXFileHandle(path).exists();
    }
    public String convertToAbsolutePathIfNeededButWithoutLeadingSlash(String path) {
        path = convertToAbsolutePathIfNeeded(path);
        return path.substring(1);
    }

    @Override
    public boolean isFile(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return !createEmbeddedLibGDXFileHandle(path).isDirectory();
    }

    @Override
    public boolean isDirectory(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return createEmbeddedLibGDXFileHandle(path).isDirectory();
    }

    @Override
    public String debug() {
        return readAssetsTxt();
    }

    @Override
    public void flush() {
        throw createUnsupportedOperationException();
    }

    @Override
    public FileType type(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
    }

    @Override
    public RegularFileType getRegularFileType(String path) {
        path = convertToAbsolutePathIfNeededButWithoutLeadingSlash(path);
        return isTextFile(createEmbeddedLibGDXFileHandle(path)) ? RegularFileType.TEXT : RegularFileType.BINARY;
    }

    private boolean isTextFile(com.badlogic.gdx.files.FileHandle file) {
        String content = file.readString();
        return isTextFile(content);
    }

    private com.badlogic.gdx.files.FileHandle createEmbeddedLibGDXFileHandle(String name) {

        if (Pixel.app().isOneOfPlatforms(Platform.ANDROID, Platform.WEB)) {
            return Gdx.files.internal(name);
        } else {
            return Gdx.files.classpath(name);

        }
    }

    @Override
    public byte[] backup(String methodName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void restore(String methodName, byte[] data) {
        throw createUnsupportedOperationException();
    }

    @Override
    public boolean isReadonly() {
        return true;
    }

    @Override
    public void clear() {
        throw createUnsupportedOperationException();
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
    public FileSystemType getFileSystemType() {
        return FileSystemType.ASSETS;
    }

}
