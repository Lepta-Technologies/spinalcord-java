// Copyright 2023 Lepta Technologies
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.leptatech.spinalcord.fields;

import com.leptatech.spinalcord.exceptions.FieldNotSetException;
import com.leptatech.spinalcord.utils.BytesBuffer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UInt16FieldTest
{
    @Test
    public void testClear()
    {
        // Check UInt16Field clear method
        UInt16Field uInt16Field = new UInt16Field("uInt16Field");
        uInt16Field.set((short)0);
        uInt16Field.clear();
        try
        {
            uInt16Field.get();
            Assert.fail();
        }
        catch (FieldNotSetException e)
        {
        }
    }

    @Test
    public void testFromToBytes() throws IOException, FieldNotSetException {
        Path resourcePath = Paths.get("src", "test", "resources", "com", "leptatech", "spinalcord",
                "fields", "uInt16.bin");
        BytesBuffer buffer = new BytesBuffer(Files.readAllBytes(resourcePath));
        UInt16Field uInt16Field = new UInt16Field("uInt16Field");
        // Check min value
        BytesBuffer minBuffer = new BytesBuffer(buffer.read(3));
        uInt16Field.fromBytes(minBuffer);
        Assert.assertEquals(new Short((short)0), uInt16Field.get());
        Assert.assertArrayEquals(minBuffer.get(), uInt16Field.toBytes().get());
        // Check max value
        BytesBuffer maxBuffer = new BytesBuffer(buffer.read(3));
        uInt16Field.fromBytes(maxBuffer);
        Assert.assertEquals(new Short((short)65535), uInt16Field.get());
        Assert.assertArrayEquals(maxBuffer.get(), uInt16Field.toBytes().get());
    }

    @Test
    public void testGetBytesLength()
    {
        // Check UInt16Field getBytesLength method
        UInt16Field uInt16Field = new UInt16Field("uInt16Field");
        Assert.assertEquals(1, uInt16Field.getBytesLength());
        uInt16Field.set((short)0);
        Assert.assertEquals(3, uInt16Field.getBytesLength());
    }

    @Test
    public void testGetColumnName()
    {
        // Check UInt16Field getColumnName method
        UInt16Field uInt16Field = new UInt16Field("uInt16Field");
        Assert.assertEquals("uInt16Field", uInt16Field.getColumnName());
    }

    @Test
    public void testGetSet()
    {
        // Check UInt16Field get and set methods
        UInt16Field uInt16Field = new UInt16Field("uInt16Field");
        // Check an exception is thrown when attempting to call get before set
        try
        {
            uInt16Field.get();
            Assert.fail();
        }
        catch (FieldNotSetException e)
        {
        }
        // Set and check value
        uInt16Field.set((short)0);
        try
        {
            Assert.assertEquals(new Short((short)0), uInt16Field.get());
        }
        catch (FieldNotSetException e)
        {
            Assert.fail();
        }
    }
}
