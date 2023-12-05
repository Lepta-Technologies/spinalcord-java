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

import com.leptatech.spinalcord.fields.IField;
import com.leptatech.spinalcord.utils.BytesBuffer;

abstract class Model implements IModel
{
    public void clear()
    {
        // Clear fields
        for (IField field : getFields())
        {
            field.clear();
        }
    }

    public void fromBytes(BytesBuffer buffer)
    {
        // Set fields
        for (IField field : getFields())
        {
            field.fromBytes(buffer);
        }
    }

    public int getBytesLength()
    {
        int n = 0;
        // Sum fields
        for (IField field : getFields())
        {
            n += field.getBytesLength();
        }

        return n;
    }

    public BytesBuffer toBytes()
    {
        BytesBuffer buffer = new BytesBuffer();
        // Append fields
        for (IField field : getFields())
        {
            buffer.append(field.toBytes());
        }

        return buffer;
    }

    public abstract IField[] getFields();
}
