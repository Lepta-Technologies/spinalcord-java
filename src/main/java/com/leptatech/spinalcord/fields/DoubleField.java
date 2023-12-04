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
import com.leptatech.spinalcord.utils.BytesConverter;

public class DoubleField extends BaseField<Double>
{
    private Double _value;

    public DoubleField(String columnName)
    {
        super(columnName);
    }

    @Override
    public void clear()
    {
        if (isSet())
        {
            toggleIsSet();
        }
    }

    @Override
    public void fromBytes(BytesBuffer buffer)
    {
        if (BytesConverter.toBoolean(buffer.read(1)))
        {
            set(BytesConverter.toDouble(buffer.read(8)));
        }
    }

    @Override
    public Double get() throws FieldNotSetException
    {
        if (!isSet())
        {
            throw new FieldNotSetException();
        }

        return _value;
    }

    public int getBytesLength()
    {
        if (isSet())
        {
            return super.getBytesLength() + 8;
        }

        return super.getBytesLength();
    }

    @Override
    public void set(Double value)
    {
        _value = value;
        if (!isSet())
        {
            toggleIsSet();
        }
    }

    @Override
    public BytesBuffer toBytes()
    {
        BytesBuffer buffer = new BytesBuffer();
        buffer.append(BytesConverter.fromBoolean(isSet()));
        try
        {
            buffer.append(BytesConverter.fromDouble(get()));
        }
        catch (FieldNotSetException e)
        {
        }

        return buffer;
    }
}
