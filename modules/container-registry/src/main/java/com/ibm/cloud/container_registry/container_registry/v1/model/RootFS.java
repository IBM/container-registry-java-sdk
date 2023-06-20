/*
 * (C) Copyright IBM Corp. 2023.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.ibm.cloud.container_registry.container_registry.v1.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * RootFS contains information about the root filesystem of a container image.
 */
public class RootFS extends GenericModel {

  @SerializedName("BaseLayer")
  protected String baseLayer;
  @SerializedName("Layers")
  protected List<String> layers;
  @SerializedName("Type")
  protected String type;

  protected RootFS() { }

  /**
   * Gets the baseLayer.
   *
   * Descriptor for the base layer in the image.
   *
   * @return the baseLayer
   */
  public String getBaseLayer() {
    return baseLayer;
  }

  /**
   * Gets the layers.
   *
   * Descriptors for each layer in the image.
   *
   * @return the layers
   */
  public List<String> getLayers() {
    return layers;
  }

  /**
   * Gets the type.
   *
   * The type of filesystem.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }
}

