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

package com.leptatech.spinalcord.models;

import com.leptatech.spinalcord.exceptions.FieldNotSetException;
import com.leptatech.spinalcord.fields.DoubleField;
import com.leptatech.spinalcord.fields.IField;
import com.leptatech.spinalcord.fields.Int32Field;
import com.leptatech.spinalcord.fields.UInt16Field;
import com.leptatech.spinalcord.utils.BytesBuffer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest
{
    @Test
    public void testClear()
    {
        // Check Model clear method
        TestModel model = new TestModel();
        model.doubleField.set(0.0);
        model.clear();
        try
        {
            model.doubleField.get();
            fail();
        }
        catch (FieldNotSetException e)
        {
        }
    }

    @Test
    public void testFromToBytes() throws IOException, FieldNotSetException {
        // Check Model fromBytes method
        Path resourcePath = Paths.get("src", "test", "resources", "com", "leptatech", "spinalcord",
                "models", "testmodel.bin");
        BytesBuffer buffer = new BytesBuffer(Files.readAllBytes(resourcePath));
        TestModel model = new TestModel();
        model.fromBytes(buffer);
        // Check field values
        assertEquals(new Double(0.0), model.doubleField.get());
        assertEquals(new Integer(0), model.int32Field.get());
        assertEquals(new Short((short)0), model.uInt16Field.get());
        // Check Model toBytes method
        assertArrayEquals(buffer.get(), model.toBytes().get());
    }

    @Test
    public void testGetBytesLength() throws IOException {
        // Check Model getBytesLength method
        Path resourcePath = Paths.get("src", "test", "resources", "com", "leptatech", "spinalcord",
                "models", "testmodel.bin");
        BytesBuffer buffer = new BytesBuffer(Files.readAllBytes(resourcePath));
        TestModel model = new TestModel();
        model.fromBytes(buffer);
        assertEquals(17, model.getBytesLength());
    }

    @Test
    public void testGetFields()
    {
        // Check Model getFields method
        IField[] referenceFields = new IField[3];
        referenceFields[0] = new DoubleField("doubleField");
        referenceFields[1] = new Int32Field("int32Field");
        referenceFields[2] = new UInt16Field("uInt16Field");
        TestModel model = new TestModel();
        IField[] modelFields = model.getFields();
        for (int i = 0; i < modelFields.length; i++)
        {
            assertEquals(referenceFields[i].getClass(), modelFields[i].getClass());
        }
    }
}
