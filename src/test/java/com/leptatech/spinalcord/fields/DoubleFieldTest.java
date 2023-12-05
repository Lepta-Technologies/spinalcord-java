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

public class DoubleFieldTest
{
    @Test
    public void testClear()
    {
        // Check DoubleField clear method
        DoubleField doubleField = new DoubleField("doubleField");
        doubleField.set(0.0);
        doubleField.clear();
        try
        {
            doubleField.get();
            fail();
        }
        catch (FieldNotSetException e)
        {
        }
    }

    @Test
    public void testFromToBytes() throws IOException, FieldNotSetException {
        Path resourcePath = Paths.get("src", "test", "resources", "com", "leptatech", "spinalcord",
                "fields", "double.bin");
        BytesBuffer buffer = new BytesBuffer(Files.readAllBytes(resourcePath));
        DoubleField doubleField = new DoubleField("doubleField");
        // Check min value
        BytesBuffer minBuffer = new BytesBuffer(buffer.read(9));
        doubleField.fromBytes(minBuffer);
        assertEquals(new Double(1.7e-308), doubleField.get());
        assertArrayEquals(minBuffer.get(), doubleField.toBytes().get());
        // Check max value
        BytesBuffer maxBuffer = new BytesBuffer(buffer.read(9));
        doubleField.fromBytes(maxBuffer);
        assertEquals(new Double(1.7e+308), doubleField.get());
        assertArrayEquals(maxBuffer.get(), doubleField.toBytes().get());
    }

    @Test
    public void testGetBytesLength()
    {
        // Check DoubleField getBytesLength method
        DoubleField doubleField = new DoubleField("doubleField");
        assertEquals(1, doubleField.getBytesLength());
        doubleField.set(0.0);
        assertEquals(9, doubleField.getBytesLength());
    }

    @Test
    public void testGetColumnName()
    {
        // Check DoubleField getColumnName method
        DoubleField doubleField = new DoubleField("doubleField");
        assertEquals("doubleField", doubleField.getColumnName());
    }

    @Test
    public void testGetSet()
    {
        // Check DoubleField get and set methods
        DoubleField doubleField = new DoubleField("doubleField");
        // Check an exception is thrown when attempting to call get before set
        try
        {
            doubleField.get();
            fail();
        }
        catch (FieldNotSetException e)
        {
        }
        // Set and check value
        doubleField.set(0.0);
        try
        {
            assertEquals(new Double(0.0), doubleField.get());
        }
        catch (FieldNotSetException e)
        {
            fail();
        }
    }
}
