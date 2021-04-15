/*
 * (C) Copyright IBM Corp. 2021.
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

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The createNamespace options.
 */
public class CreateNamespaceOptions extends GenericModel {

  protected String name;
  protected String xAuthResourceGroup;

  /**
   * Builder.
   */
  public static class Builder {
    private String name;
    private String xAuthResourceGroup;

    private Builder(CreateNamespaceOptions createNamespaceOptions) {
      this.name = createNamespaceOptions.name;
      this.xAuthResourceGroup = createNamespaceOptions.xAuthResourceGroup;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param name the name
     */
    public Builder(String name) {
      this.name = name;
    }

    /**
     * Builds a CreateNamespaceOptions.
     *
     * @return the new CreateNamespaceOptions instance
     */
    public CreateNamespaceOptions build() {
      return new CreateNamespaceOptions(this);
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the CreateNamespaceOptions builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Set the xAuthResourceGroup.
     *
     * @param xAuthResourceGroup the xAuthResourceGroup
     * @return the CreateNamespaceOptions builder
     */
    public Builder xAuthResourceGroup(String xAuthResourceGroup) {
      this.xAuthResourceGroup = xAuthResourceGroup;
      return this;
    }
  }

  protected CreateNamespaceOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.name,
      "name cannot be empty");
    name = builder.name;
    xAuthResourceGroup = builder.xAuthResourceGroup;
  }

  /**
   * New builder.
   *
   * @return a CreateNamespaceOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the name.
   *
   * The name of the namespace.
   *
   * @return the name
   */
  public String name() {
    return name;
  }

  /**
   * Gets the xAuthResourceGroup.
   *
   * The ID of the resource group that the namespace will be created within.
   *
   * @return the xAuthResourceGroup
   */
  public String xAuthResourceGroup() {
    return xAuthResourceGroup;
  }
}

