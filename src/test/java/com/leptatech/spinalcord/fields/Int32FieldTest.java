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
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class Int32FieldTest
{
    @Test
    public void testClear()
    {
        // Check Int32Field clear method
        Int32Field int32Field = new Int32Field("int32Field");
        int32Field.set(0);
        int32Field.clear();
        try
        {
            int32Field.get();
            fail();
        }
        catch (FieldNotSetException e)
        {
        }
    }

    @Test
    public void testFromToBytes() throws IOException, FieldNotSetException {
        Path resourcePath = Paths.get("src", "test", "resources", "com", "leptatech", "spinalcord",
                "fields", "int32.bin");
        BytesBuffer buffer = new BytesBuffer(Files.readAllBytes(resourcePath));
        Int32Field int32Field = new Int32Field("int32Field");
        // Check min value
        BytesBuffer minBuffer = new BytesBuffer(buffer.read(5));
        int32Field.fromBytes(minBuffer);
        assertEquals(new Integer(-2147483648), int32Field.get());
        assertArrayEquals(minBuffer.get(), int32Field.toBytes().get());
        // Check max value
        BytesBuffer maxBuffer = new BytesBuffer(buffer.read(5));
        int32Field.fromBytes(maxBuffer);
        assertEquals(new Integer(2147483647), int32Field.get());
        assertArrayEquals(maxBuffer.get(), int32Field.toBytes().get());
    }

    @Test
    public void testGetBytesLength()
    {
        // Check Int32Field getBytesLength method
        Int32Field int32Field = new Int32Field("int32Field");
        assertEquals(1, int32Field.getBytesLength());
        int32Field.set(0);
        assertEquals(5, int32Field.getBytesLength());
    }

    @Test
    public void testGetColumnName()
    {
        // Check Int32Field getColumnName method
        Int32Field int32Field = new Int32Field("int32Field");
        assertEquals("int32Field", int32Field.getColumnName());
    }

    @Test
    public void testGetSet()
    {
        // Check Int32Field get and set methods
        Int32Field int32Field = new Int32Field("int32Field");
        // Check an exception is thrown when attempting to call get before set
        try
        {
            int32Field.get();
            fail();
        }
        catch (FieldNotSetException e)
        {
        }
        // Set and check value
        int32Field.set(0);
        try
        {
            assertEquals(new Integer(0), int32Field.get());
        }
        catch (FieldNotSetException e)
        {
            fail();
        }
    }
}
