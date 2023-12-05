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

public class BytesBuffer
{
    private byte[] _bytes = new byte[0];
    private int _cursorI = 0;

    public BytesBuffer()
    {
    }

    public BytesBuffer(byte[] bytes)
    {
        _bytes = bytes;
    }

    public void append(byte[] bytes)
    {
        byte[] newBytes = new byte[_bytes.length + bytes.length];
        if (_bytes.length > 0)
        {
            System.arraycopy(_bytes, 0, newBytes, 0, _bytes.length);
        }
        System.arraycopy(bytes, 0, newBytes, _bytes.length, bytes.length);
        _bytes = newBytes;
    }

    public void append(BytesBuffer buffer)
    {
        append(buffer.get());
    }

    public byte[] get()
    {
        return _bytes;
    }

    public byte[] read(int n)
    {
        byte[] bytes = new byte[n];
        System.arraycopy(_bytes, _cursorI, bytes, 0, n);
        _cursorI += n;
        return bytes;
    }
}
