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

package com.leptatech.spinalcord.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BytesConverter
{
    public static byte[] fromBoolean(boolean value)
    {
        return new byte[] {(byte)(value ? 1 : 0)};
    }

    public static byte[] fromDouble(Double value)
    {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putDouble(value);
        return buffer.array();
    }

    public static byte[] fromInt32(Integer value)
    {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(value);
        return buffer.array();
    }

    public static byte[] fromUInt16(Short value)
    {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putShort(value);
        return buffer.array();
    }

    public static boolean toBoolean(byte[] bytes)
    {
        return bytes[0] == (byte)1;
    }

    public static Double toDouble(byte[] bytes)
    {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getDouble();
    }

    public static Integer toInt32(byte[] bytes)
    {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getInt();
    }

    public static Short toUInt16(byte[] bytes)
    {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getShort();
    }
}
