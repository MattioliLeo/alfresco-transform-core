/*
 * #%L
 * Alfresco Transform Core
 * %%
 * Copyright (C) 2005 - 2019 Alfresco Software Limited
 * %%
 * This file is part of the Alfresco software.
 * -
 * If the software was purchased under a paid Alfresco license, the terms of
 * the paid license agreement will prevail.  Otherwise, the software is
 * provided under the following open source license terms:
 * -
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * -
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * -
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package org.alfresco.transform.base;

/**
 * @author Cezar Leahu
 */
public class FileInfo
{
    private final String mimeType;
    private final String extension;
    private final String path;
    private final boolean exactMimeType;

    public FileInfo(final String mimeType, final String extension, final String path, final boolean exactMimeType)
    {
        this.mimeType = mimeType;
        this.extension = extension;
        this.path = path;
        this.exactMimeType = exactMimeType;
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public String getExtension()
    {
        return extension;
    }

    public String getPath()
    {
        return path;
    }

    public boolean isExactMimeType()
    {
        return exactMimeType;
    }

    public static FileInfo testFile(final String mimeType, final String extension, final String path, final boolean exactMimeType)
    {
        return new FileInfo(mimeType, extension, path, exactMimeType);
    }

    public static FileInfo testFile(final String mimeType, final String extension, final String path)
    {
        return new FileInfo(mimeType, extension, path, false);
    }

    @Override
    public String toString()
    {
        return path;
    }
}