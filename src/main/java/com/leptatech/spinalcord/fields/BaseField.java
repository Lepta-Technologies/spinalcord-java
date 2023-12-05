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

public abstract class BaseField<T> implements IField
{
    private String _columnName;
    private boolean _isSet;

    public BaseField(String columnName)
    {
        _columnName = columnName;
    }

    public int getBytesLength()
    {
        return 1;
    }

    public String getColumnName()
    {
        return _columnName;
    }

    public boolean isSet()
    {
        return _isSet;
    }

    public void toggleIsSet()
    {
        _isSet = !_isSet;
    }

    public abstract void clear();
    public abstract void fromBytes(BytesBuffer buffer);
    public abstract T get() throws FieldNotSetException;
    public abstract void set(T value);
    public abstract BytesBuffer toBytes();
}
