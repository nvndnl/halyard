curl -L -O https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/tar/elasticsearch/2.4.6/elasticsearch-2.4.6.tar.gz
tar -xvf elasticsearch-2.4.6.tar.gz
elasticsearch-2.4.6/bin/elasticsearch -d

sudo curl -X "PUT" "http://localhost:9200/_template/entity_tags" \
     -H "Content-Type: application/json" \
     -d $'{
  "order": 0,
  "template": "tags_v*",
  "settings": {
    "index": {
      "number_of_shards": "3",
      "number_of_replicas": "0",
      "refresh_interval": "1s"
    }
  },
  "mappings": {
    "_default_": {
      "dynamic": "false",
      "dynamic_templates": [
        {
          "tags_template": {
            "path_match": "tagsMetadata",
            "mapping": {
              "index": "no"
            }
          }
        },
        {
          "entityRef_template": {
            "path_match": "entityRef.*",
            "mapping": {
              "index": "not_analyzed"
            }
          }
        }
      ],
      "properties": {
        "entityRef": {
          "properties": {
            "accountId": {
              "index": "not_analyzed",
              "type": "string"
            },
            "application": {
              "index": "not_analyzed",
              "type": "string"
            },
            "entityType": {
              "index": "not_analyzed",
              "type": "string"
            },
            "cloudProvider": {
              "index": "not_analyzed",
              "type": "string"
            },
            "entityId": {
              "index": "not_analyzed",
              "type": "string"
            },
            "region": {
              "index": "not_analyzed",
              "type": "string"
            },
            "account": {
              "index": "not_analyzed",
              "type": "string"
            }
          }
        },
        "tags": {
          "type": "nested",
          "properties": {
            "valueType": {
              "index": "not_analyzed",
              "type": "string"
            },
            "name": {
              "index": "not_analyzed",
              "type": "string"
            },
            "namespace": {
              "index": "not_analyzed",
              "type": "string"
            },
            "value": {
              "index": "not_analyzed",
              "type": "string"
            }
          }
        }
      }
    }
  },
  "aliases": {}
}'

sudo curl -X "PUT" "http://localhost:9200/tags_v2" \
     -H "Content-Type: application/json" \
     -d $'{
    "settings" : {
        "number_of_shards" : 3,
        "number_of_replicas" : 0
    }
}'

sudo curl -X "PUT" "http://localhost:7002/admin/tags/reindex"
