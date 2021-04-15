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
 * The updateQuota options.
 */
public class UpdateQuotaOptions extends GenericModel {

  protected Long storageMegabytes;
  protected Long trafficMegabytes;

  /**
   * Builder.
   */
  public static class Builder {
    private Long storageMegabytes;
    private Long trafficMegabytes;

    private Builder(UpdateQuotaOptions updateQuotaOptions) {
      this.storageMegabytes = updateQuotaOptions.storageMegabytes;
      this.trafficMegabytes = updateQuotaOptions.trafficMegabytes;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a UpdateQuotaOptions.
     *
     * @return the new UpdateQuotaOptions instance
     */
    public UpdateQuotaOptions build() {
      return new UpdateQuotaOptions(this);
    }

    /**
     * Set the storageMegabytes.
     *
     * @param storageMegabytes the storageMegabytes
     * @return the UpdateQuotaOptions builder
     */
    public Builder storageMegabytes(long storageMegabytes) {
      this.storageMegabytes = storageMegabytes;
      return this;
    }

    /**
     * Set the trafficMegabytes.
     *
     * @param trafficMegabytes the trafficMegabytes
     * @return the UpdateQuotaOptions builder
     */
    public Builder trafficMegabytes(long trafficMegabytes) {
      this.trafficMegabytes = trafficMegabytes;
      return this;
    }
  }

  protected UpdateQuotaOptions(Builder builder) {
    storageMegabytes = builder.storageMegabytes;
    trafficMegabytes = builder.trafficMegabytes;
  }

  /**
   * New builder.
   *
   * @return a UpdateQuotaOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the storageMegabytes.
   *
   * Storage quota in megabytes. The value -1 denotes "Unlimited".
   *
   * @return the storageMegabytes
   */
  public Long storageMegabytes() {
    return storageMegabytes;
  }

  /**
   * Gets the trafficMegabytes.
   *
   * Traffic quota in megabytes. The value -1 denotes "Unlimited".
   *
   * @return the trafficMegabytes
   */
  public Long trafficMegabytes() {
    return trafficMegabytes;
  }
}

