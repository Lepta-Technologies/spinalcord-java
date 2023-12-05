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

import com.leptatech.spinalcord.fields.DoubleField;
import com.leptatech.spinalcord.fields.IField;
import com.leptatech.spinalcord.fields.Int32Field;
import com.leptatech.spinalcord.fields.UInt16Field;

public class TestModel extends Model
{
    public final DoubleField doubleField = new DoubleField("doubleField");
    public final Int32Field int32Field = new Int32Field("int32Field");
    public final UInt16Field uInt16Field = new UInt16Field("uInt16Field");

    @Override
    public IField[] getFields() {
        return new IField[] {doubleField, int32Field, uInt16Field};
    }
}
