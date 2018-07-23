
package com.netflix.spinnaker.halyard.deploy.spinnaker.v1.service.local.git;

import com.netflix.spinnaker.halyard.config.model.v1.node.DeploymentConfiguration;
import com.netflix.spinnaker.halyard.core.resource.v1.StringReplaceJarResource;
import com.netflix.spinnaker.halyard.deploy.deployment.v1.DeploymentDetails;
import com.netflix.spinnaker.halyard.deploy.services.v1.ArtifactService;
import com.netflix.spinnaker.halyard.deploy.spinnaker.v1.SpinnakerRuntimeSettings;
import com.netflix.spinnaker.halyard.deploy.spinnaker.v1.service.ElasticsearchService;
import com.netflix.spinnaker.halyard.deploy.spinnaker.v1.service.ServiceSettings;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class LocalGitElasticsearchService extends ElasticsearchService implements LocalGitService<ElasticsearchService.Elasticsearch> {
  String startCommand = "";

  @Autowired
  String gitRoot;

  @Override
  public ServiceSettings buildServiceSettings(DeploymentConfiguration deploymentConfiguration) {
    System.out.println(deploymentConfiguration.getFeatures().getEntityTags());
    return new Settings().setArtifactId("elasticsearch")
        .setHost(getDefaultHost())
        .setEnabled(deploymentConfiguration.getFeatures().getEntityTags());
  }

  @Autowired
  ArtifactService artifactService;

  @Override
  public String installArtifactCommand(DeploymentDetails deploymentDetails) {
    return "";
    // Map<String, Object> bindings = new HashMap<>();
    // bindings.put("version", deploymentDetails.getArtifactVersion(getArtifact().getName()));
    // return new StringReplaceJarResource("/services/elasticsearch/index.sh")
    //     .setBindings(bindings)
    //     .toString();
  }

  @Override
  public void collectLogs(DeploymentDetails details, SpinnakerRuntimeSettings runtimeSettings) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String prepArtifactCommand(DeploymentDetails deploymentDetails) {
    return "";
    // Map<String, Object> bindings = new HashMap<>();
    // bindings.put("version", deploymentDetails.getArtifactVersion(getArtifact().getName()));
    // return new StringReplaceJarResource("/services/elasticsearch/install.sh")
    //     .setBindings(bindings)
    //     .toString();
  }
}
