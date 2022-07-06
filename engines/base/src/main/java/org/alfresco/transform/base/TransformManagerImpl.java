/*
 * #%L
 * Alfresco Transform Core
 * %%
 * Copyright (C) 2005 - 2022 Alfresco Software Limited
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

import org.alfresco.transform.base.fs.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class TransformManagerImpl implements TransformManager
{
    private static final Logger logger = LoggerFactory.getLogger(TransformManagerImpl.class);

    private HttpServletRequest request;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String sourceMimetype;
    private String targetMimetype;
    private File sourceFile;
    private File targetFile;
    private boolean createSourceFileCalled;
    private boolean createTargetFileCalled;
    private boolean targetFileCreated;

    private TransformManagerImpl()
    {
    }

    public void init()
    {
        request = null;
        inputStream = null;
        outputStream = null;
        sourceFile = null;
        targetFile = null;
        createSourceFileCalled = false;
        createTargetFileCalled = false;
        targetFileCreated = false;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public void setInputStream(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public void setOutputStream(OutputStream outputStream)
    {
        this.outputStream = outputStream;
    }

    public void setSourceMimetype(String sourceMimetype)
    {
        this.sourceMimetype = sourceMimetype;
    }

    public void setTargetMimetype(String targetMimetype)
    {
        this.targetMimetype = targetMimetype;
    }

    public File getSourceFile()
    {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile)
    {
        this.sourceFile = sourceFile;
    }

    public File getTargetFile() {
        return targetFile;
    }

    void setTargetFile(File targetFile)
    {
        this.targetFile = targetFile;
    }

    public boolean isCreateSourceFileCalled()
    {
        return createSourceFileCalled;
    }

    public boolean isCreateTargetFileCalled()
    {
        return createTargetFileCalled;
    }

    @Override public File createSourceFile()
    {
        if (createSourceFileCalled)
        {
            throw new IllegalStateException("createSourceFile has already been called");
        }
        createSourceFileCalled = true;

        if (sourceFile == null)
        {
            sourceFile = FileManager.createSourceFile(request, inputStream, sourceMimetype);
        }
        return sourceFile;
    }

    @Override public File createTargetFile()
    {
        if (createTargetFileCalled)
        {
            throw new IllegalStateException("createTargetFile has already been called");
        }
        createTargetFileCalled = true;

        if (targetFile == null)
        {
            targetFile = FileManager.createTargetFile(request, sourceMimetype, targetMimetype);
            targetFileCreated = true;
        }
        return targetFile;
    }

    public void ifUsedCopyTargetFileToOutputStream()
    {
        if (targetFileCreated)
        {
            FileManager.copyFileToOutputStream(targetFile, outputStream);
        }
    }

    public void deleteSourceFileIfExists()
    {
        if (sourceFile != null && sourceFile.delete() == false)
        {
            logger.error("Failed to delete temporary source file "+sourceFile.getPath());
        }
        sourceFile = null;
    }

    public void deleteTargetFileIfExists()
    {
        if (targetFile != null && targetFile.delete() == false)
        {
            logger.error("Failed to delete temporary target file "+targetFile.getPath());
        }
        targetFile = null;
        targetFileCreated = false;
    }

    @Override
    public OutputStream respondWithFragment(Integer index)
    {
        if (request != null)
        {
            throw new IllegalStateException(
                    " Fragments may only be sent with asynchronous requests. This a synchronous http request");
        }

        throw new UnsupportedOperationException("Not currently supported");
    }

    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private final TransformManagerImpl transformManager = new TransformManagerImpl();

        public TransformManagerImpl build()
        {
            return transformManager;
        }

        public Builder withSourceMimetype(String sourceMimetype)
        {
            transformManager.sourceMimetype = sourceMimetype;
            return this;
        }

        public Builder withTargetMimetype(String targetMimetype)
        {
            transformManager.targetMimetype = targetMimetype;
            return this;
        }

        public Builder withInputStream(InputStream inputStream)
        {
            transformManager.inputStream = inputStream;
            return this;
        }

        public Builder withOutputStream(OutputStream outputStream)
        {
            transformManager.outputStream = outputStream;
            return this;
        }

        public Builder withRequest(HttpServletRequest request)
        {
            transformManager.request = request;
            return this;
        }

        public Builder withTargetFile(File targetFile)
        {
            transformManager.targetFile = targetFile;
            return this;
        }
    }
}
