package com.pixelgamelibrary.backend.libgdx.storage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.pixelgamelibrary.api.GameI;
import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.Platform;
import com.pixelgamelibrary.api.interfaces.AppI;
import com.pixelgamelibrary.api.interfaces.AssetI;
import com.pixelgamelibrary.api.interfaces.AudioI;
import com.pixelgamelibrary.api.interfaces.GraphicsI;
import com.pixelgamelibrary.api.interfaces.InputI;
import com.pixelgamelibrary.api.interfaces.InternalI;
import com.pixelgamelibrary.api.interfaces.NetI;
import com.pixelgamelibrary.api.interfaces.PixelBackend;
import com.pixelgamelibrary.api.interfaces.StorageI;
import com.pixelgamelibrary.api.interfaces.UtilsI;
import com.pixelgamelibrary.api.storage.RegularFileType;
import com.pixelgamelibrary.api.storage.StorageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import static org.mockito.Mockito.*;

class DesktopAndroidStorageTest {

    private DesktopAndroidStorage storage;
    private FileHandle mockFileHandle;

       //
    @BeforeAll
    static void setUpBeforeAll() {
        PixelBackend dummyPixelBackend = new PixelBackend() {
            @Override
            public AppI app() {
                return new AppI() {
                    @Override
                    public Platform getPlatform() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void exit() {
                    }

                    @Override
                    public void log(String msg) {

                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void debug(String msg) {

                    }

                    @Override
                    public void warn(String msg) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setAppName(String appName) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public String getAppName() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public boolean isAppNameSet() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public void setGame(GameI game) {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public GameI getGame() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                };
            }

            @Override
            public GraphicsI graphics() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public AudioI audio() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public InputI input() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public NetI net() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public AssetI asset() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public StorageI storage() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public UtilsI utils() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public InternalI internal() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        Pixel.initBackend(dummyPixelBackend);
}
    @BeforeEach
    void setUp() {
        mockFileHandle = mock(FileHandle.class);
        //
        Gdx.files = Mockito.mock(com.badlogic.gdx.Files.class);
           // Mock FileHandle
        //FileHandle mockFileHandle = Mockito.mock(FileHandle.class);
        when(Gdx.files.local(Mockito.anyString())).thenReturn(mockFileHandle);
        
        
     
        //
                
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

    @Disabled @Test
    void testCreateDirectory_ExistingDirectory_ShouldReturnWarning() {
        when(storage.createLibGdxFileHandle(anyString())).thenReturn(mockFileHandle);
        when(mockFileHandle.exists()).thenReturn(true);
        
        
        String result = storage.createDirectory("existingDir");
        assertEquals("Directory already exists: existingDir", result);
    }

    @Test
    void testCreateDirectory_NewDirectory_ShouldCreateDirectory() {
        when(mockFileHandle.exists()).thenReturn(false);
        String result = storage.createDirectory("newDir");
        assertEquals("", result);
        verify(mockFileHandle, times(2)).mkdirs();
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

    @Disabled @Test
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

    @Disabled @Test
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

    @Disabled @Test
    void testGetRegularFileType_ShouldReturnBinaryForBinaryFile() {
        when(mockFileHandle.readString()).thenThrow(new RuntimeException());
        assertEquals(RegularFileType.BINARY, storage.getRegularFileType("testFile.bin"));
    }
}
