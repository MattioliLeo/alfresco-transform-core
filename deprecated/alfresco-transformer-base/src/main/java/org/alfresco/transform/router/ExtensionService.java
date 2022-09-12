/*
 * #%L
 * Alfresco Transform Model
 * %%
 * Copyright (C) 2005 - 2022 Alfresco Software Limited
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package org.alfresco.transform.router;

/**
 * @deprecated will be removed in a future release. Replaced by alfresco-base-t-engine.
 *
 * This class previously existed in the alfresco-transform-model. It now exists in the deprecated
 * alfresco-transform-base and extends the new class. This should to make it easier to use the deprecated module
 * by custom transformers that have not been converted to the new base.
 */
public class ExtensionService extends org.alfresco.transform.common.ExtensionService
{
    private ExtensionService()
    {
    }
}
