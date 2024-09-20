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
package com.pixelgamelibrary.backend.libgdx;

import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.XmlReader;
import com.pixelgamelibrary.api.interfaces.XmlElement;
import java.util.List;
import java.util.Map;
import com.badlogic.gdx.utils.compression.Lzma;
import com.pixelgamelibrary.api.PixelException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.pixelgamelibrary.api.interfaces.Utils;
import com.pixelgamelibrary.api.interfaces.App;
import com.pixelgamelibrary.api.utils.CollectionUtils;
import com.pixelgamelibrary.api.utils.ReflectionUtils;

/**
 *
 * @author robertvokac
 */
public class UtilsLibGDXImpl implements Utils {

    private final App appI;

    public UtilsLibGDXImpl(App appI) {
        this.appI = appI;
    }

    @Override
    public XmlElement parseXml(String xmlString) {
        XmlReader.Element root = new XmlReader().parse(xmlString);
        return new ElementLibGDXImpl(root);

    }

    public byte[] decodeBase64AsByteArray(String string) {
        return Base64Coder.decode(string);
    }

    public String encodeToBase64(byte[] data) {
        return String.valueOf(Base64Coder.encode(data));
    }

    @Override
    public List<String> listSupportedCompressions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public byte[] compress(byte[] data, String compression, Map<String, String> arguments) {
        switch (compression) {
            case "LZMA": {
                try {
                    return compressLzma(data);
                } catch (IOException ex) {
                    appI.error(ex.getMessage());
                    throw new PixelException(ex.getMessage());
                }
            }

            default:
                throw new UnsupportedOperationException("This type of compression is not supported: " + compression);
        }

    }

    @Override
    public byte[] decompress(byte[] data, String compression) {
        switch (compression) {
            case "LZMA": {
                try {
                    return decompressLzma(data);
                } catch (IOException ex) {
                    appI.error(ex.getMessage());
                    throw new PixelException(ex.getMessage());
                }
            }

            default:
                throw new UnsupportedOperationException("This type of compression is not supported: " + compression);
        }
    }

    public byte[] compressLzma(byte[] data) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Lzma.compress(new ByteArrayInputStream(data), outputStream);
        return outputStream.toByteArray();
    }

    public byte[] decompressLzma(byte[] compressedData) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Lzma.decompress(inputStream, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public ReflectionUtils reflection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CollectionUtils collections() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
