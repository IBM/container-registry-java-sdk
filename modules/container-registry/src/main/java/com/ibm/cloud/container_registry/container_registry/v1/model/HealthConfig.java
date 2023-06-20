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
 * HealthConfig.
 */
public class HealthConfig extends GenericModel {

  @SerializedName("Interval")
  protected Long interval;
  @SerializedName("Retries")
  protected Long retries;
  @SerializedName("Test")
  protected List<String> test;
  @SerializedName("Timeout")
  protected Long timeout;

  protected HealthConfig() { }

  /**
   * Gets the interval.
   *
   * A Duration represents the elapsed time between two instants as an int64 nanosecond count.
   *
   * @return the interval
   */
  public Long getInterval() {
    return interval;
  }

  /**
   * Gets the retries.
   *
   * The number of consecutive failures needed to consider a container as unhealthy. Zero means inherit.
   *
   * @return the retries
   */
  public Long getRetries() {
    return retries;
  }

  /**
   * Gets the test.
   *
   * The test to perform to check that the container is healthy. An empty slice means to inherit the default. The
   * options are:
   * {} : inherit healthcheck
   * {"NONE"} : disable healthcheck
   * {"CMD", args...} : exec arguments directly
   * {"CMD-SHELL", command} : run command with system's default shell.
   *
   * @return the test
   */
  public List<String> getTest() {
    return test;
  }

  /**
   * Gets the timeout.
   *
   * A Duration represents the elapsed time between two instants as an int64 nanosecond count.
   *
   * @return the timeout
   */
  public Long getTimeout() {
    return timeout;
  }
}

