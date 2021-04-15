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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The listImageDigests options.
 */
public class ListImageDigestsOptions extends GenericModel {

  protected Boolean excludeTagged;
  protected Boolean excludeVa;
  protected Boolean includeIbm;
  protected List<String> repositories;

  /**
   * Builder.
   */
  public static class Builder {
    private Boolean excludeTagged;
    private Boolean excludeVa;
    private Boolean includeIbm;
    private List<String> repositories;

    private Builder(ListImageDigestsOptions listImageDigestsOptions) {
      this.excludeTagged = listImageDigestsOptions.excludeTagged;
      this.excludeVa = listImageDigestsOptions.excludeVa;
      this.includeIbm = listImageDigestsOptions.includeIbm;
      this.repositories = listImageDigestsOptions.repositories;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListImageDigestsOptions.
     *
     * @return the new ListImageDigestsOptions instance
     */
    public ListImageDigestsOptions build() {
      return new ListImageDigestsOptions(this);
    }

    /**
     * Adds an repositories to repositories.
     *
     * @param repositories the new repositories
     * @return the ListImageDigestsOptions builder
     */
    public Builder addRepositories(String repositories) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(repositories,
        "repositories cannot be null");
      if (this.repositories == null) {
        this.repositories = new ArrayList<String>();
      }
      this.repositories.add(repositories);
      return this;
    }

    /**
     * Set the excludeTagged.
     *
     * @param excludeTagged the excludeTagged
     * @return the ListImageDigestsOptions builder
     */
    public Builder excludeTagged(Boolean excludeTagged) {
      this.excludeTagged = excludeTagged;
      return this;
    }

    /**
     * Set the excludeVa.
     *
     * @param excludeVa the excludeVa
     * @return the ListImageDigestsOptions builder
     */
    public Builder excludeVa(Boolean excludeVa) {
      this.excludeVa = excludeVa;
      return this;
    }

    /**
     * Set the includeIbm.
     *
     * @param includeIbm the includeIbm
     * @return the ListImageDigestsOptions builder
     */
    public Builder includeIbm(Boolean includeIbm) {
      this.includeIbm = includeIbm;
      return this;
    }

    /**
     * Set the repositories.
     * Existing repositories will be replaced.
     *
     * @param repositories the repositories
     * @return the ListImageDigestsOptions builder
     */
    public Builder repositories(List<String> repositories) {
      this.repositories = repositories;
      return this;
    }
  }

  protected ListImageDigestsOptions(Builder builder) {
    excludeTagged = builder.excludeTagged;
    excludeVa = builder.excludeVa;
    includeIbm = builder.includeIbm;
    repositories = builder.repositories;
  }

  /**
   * New builder.
   *
   * @return a ListImageDigestsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the excludeTagged.
   *
   * ExcludeTagged returns only untagged digests.
   *
   * @return the excludeTagged
   */
  public Boolean excludeTagged() {
    return excludeTagged;
  }

  /**
   * Gets the excludeVa.
   *
   * ExcludeVA returns the digest list with no VA scan results.
   *
   * @return the excludeVa
   */
  public Boolean excludeVa() {
    return excludeVa;
  }

  /**
   * Gets the includeIbm.
   *
   * When true, API will return the IBM public images if they exist in the targeted region.
   *
   * @return the includeIbm
   */
  public Boolean includeIbm() {
    return includeIbm;
  }

  /**
   * Gets the repositories.
   *
   * Repositories in which to restrict the output. If left empty all images for the account will be returned.
   *
   * @return the repositories
   */
  public List<String> repositories() {
    return repositories;
  }
}

