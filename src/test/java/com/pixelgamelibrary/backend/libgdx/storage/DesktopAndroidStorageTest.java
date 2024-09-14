package com.pixelgamelibrary.backend.libgdx.storage;

import com.badlogic.gdx.files.FileHandle;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.storage.RegularFileType;
import com.pixelgamelibrary.api.storage.StorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DesktopAndroidStorageTest {

    private DesktopAndroidStorage storage;
    private FileHandle mockFileHandle;

    @BeforeEach
    void setUp() {
        mockFileHandle = mock(FileHandle.class);
        storage = new DesktopAndroidStorage("testStorage") {
            protected FileHandle createLibGdxFileHandle(String path) {
                return mockFileHandle;
            }

            @Override
            public Platform getPlatform() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }

    @Test
    void testConstructor_NullStorageName_ShouldThrowException() {
        Exception exception = assertThrows(StorageException.class, () -> new DesktopAndroidStorage(null) {
            
            protected FileHandle createLibGdxFileHandle(String path) {
                return mock(FileHandle.class);
            }

            @Override
            public Platform getPlatform() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        assertEquals("storageName == null || storageName.trim().isEmpty()", exception.getMessage());
    }

    @Test
    void testChangeDirectory_NonExistingDirectory_ShouldReturnErrorMessage() {
        when(mockFileHandle.exists()).thenReturn(false);
        String result = storage.changeDirectory("nonExistingDir");
        assertEquals("Directory does not exist: nonExistingDir", result);
    }

    @Test
    void testCreateDirectory_ExistingDirectory_ShouldReturnWarning() {
        when(mockFileHandle.exists()).thenReturn(true);
        String result = storage.createDirectory("existingDir");
        assertEquals("Directory already exists: existingDir", result);
    }

    @Test
    void testCreateDirectory_NewDirectory_ShouldCreateDirectory() {
        when(mockFileHandle.exists()).thenReturn(false);
        String result = storage.createDirectory("newDir");
        assertEquals("", result);
        verify(mockFileHandle).mkdirs();
    }

    @Test
    void testTouch_ShouldCreateFile() {
        String result = storage.touch("testFile.txt");
        assertEquals("", result);
        verify(mockFileHandle).writeString("", false);
    }

    @Test
    void testRemove_ShouldDeleteFile() {
        when(mockFileHandle.delete()).thenReturn(true);
        boolean result = storage.remove("testFile.txt");
        assertTrue(result);
        verify(mockFileHandle).delete();
    }

    @Test
    void testMove_ShouldMoveFile() {
        storage.move("sourceFile.txt", "targetFile.txt");
        verify(mockFileHandle).moveTo(mockFileHandle);
    }

    @Test
    void testReadString_ShouldReturnFileContent() {
        when(mockFileHandle.readString()).thenReturn("File content");
        String result = storage.readString("testFile.txt");
        assertEquals("File content", result);
    }

    @Test
    void testReadBytes_ShouldReturnFileBytes() throws IOException {
        InputStream mockInputStream = mock(InputStream.class);
        when(mockFileHandle.read()).thenReturn(mockInputStream);
        when(mockInputStream.readAllBytes()).thenReturn(new byte[]{1, 2, 3});

        byte[] result = storage.readBytes("testFile.bin");
        assertArrayEquals(new byte[]{1, 2, 3}, result);
    }

    @Test
    void testWriteString_ShouldWriteToFile() {
        String result = storage.writeString("testFile.txt", "Hello World");
        assertEquals("", result);
        verify(mockFileHandle).writeString("Hello World", false);
    }

    @Test
    void testExists_ShouldReturnTrueIfFileExists() {
        when(mockFileHandle.exists()).thenReturn(true);
        assertTrue(storage.exists("testFile.txt"));
    }

    @Test
    void testIsDirectory_ShouldReturnTrueForDirectory() {
        when(mockFileHandle.isDirectory()).thenReturn(true);
        assertTrue(storage.isDirectory("testDir"));
    }

    @Test
    void testIsFile_ShouldReturnTrueForFile() {
        when(mockFileHandle.isDirectory()).thenReturn(false);
        assertTrue(storage.isFile("testFile.txt"));
    }

    @Test
    void testDebug_ShouldReturnFileTree() {
        when(mockFileHandle.isDirectory()).thenReturn(true);
        when(mockFileHandle.name()).thenReturn("root");

        String result = storage.debug();
        assertEquals("root\n", result);
    }

    @Test
    void testRemoveDirectory_ShouldReturnTrueIfDirectoryDeleted() {
        when(mockFileHandle.deleteDirectory()).thenReturn(true);
        assertTrue(storage.removeDirectory("testDir"));
    }

    @Test
    void testGetRegularFileType_ShouldReturnTextForTextFile() {
        when(mockFileHandle.readString()).thenReturn("This is text");
        assertEquals(RegularFileType.TEXT, storage.getRegularFileType("testFile.txt"));
    }

    @Test
    void testGetRegularFileType_ShouldReturnBinaryForBinaryFile() {
        when(mockFileHandle.readString()).thenThrow(new RuntimeException());
        assertEquals(RegularFileType.BINARY, storage.getRegularFileType("testFile.bin"));
    }
}
