package com.pixelgamelibrary.backend.libgdx.assets;

import com.badlogic.gdx.Gdx;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.storage.FileType;
import com.pixelgamelibrary.api.storage.RegularFileType;
import com.pixelgamelibrary.api.storage.Storage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 *
 * @author robertvokac
 */
public class AssetsLibGDXStorage implements Storage {

    @Getter
    private final AssetsTxt assets;
    private String workingDirectory = "/";

    public AssetsLibGDXStorage() {
        assets = new AssetsTxt(readAssetsTxt());
    }

    private static String readAssetsTxt() {
        return Gdx.files.internal("assets.txt").readString();
    }

    @Override
    public Platform getPlatform() {
        return Pixel.app().getPlatform();
    }

    @Override
    public String changeDirectory(String path) {
        path = convertToAbsolutePathIfNeeded(path);
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
        path = convertToAbsolutePathIfNeeded(path);
        final String finalPath = path;
        return assets
                .list(path)
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
        path = convertToAbsolutePathIfNeeded(path);
        return createEmbeddedLibGDXFileHandle(path).readString();
    }

    @Override
    public byte[] readBytes(String path) {
        path = convertToAbsolutePathIfNeeded(path);
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
        
        path = convertToAbsolutePathIfNeeded(path);
        return createEmbeddedLibGDXFileHandle(path).exists();
    }

    @Override
    public boolean isFile(String path) {
        path = convertToAbsolutePathIfNeeded(path);
        return !createEmbeddedLibGDXFileHandle(path).isDirectory();
    }

    @Override
    public boolean isDirectory(String path) {
        path = convertToAbsolutePathIfNeeded(path);
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
        path = convertToAbsolutePathIfNeeded(path);
        return isDirectory(path) ? FileType.DIRECTORY : FileType.FILE;
    }

    @Override
    public RegularFileType getRegularFileType(String path) {
        path = convertToAbsolutePathIfNeeded(path);
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

}
