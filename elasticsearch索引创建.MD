# 创建INDEX

PUT /user/ 
````json
{
    "index" : {
        "analysis" : {
            "analyzer" : {
                "pinyin_analyzer" : {
                    "tokenizer" : "whitespace",
                    "filter" : "pinyin_first_letter_and_full_pinyin_filter"
                }
            },
            "filter" : {
                "pinyin_first_letter_and_full_pinyin_filter" : {
                    "type" : "pinyin",
                    "keep_first_letter" : true,
                    "keep_full_pinyin" : true,
                    "keep_none_chinese" : true,
                    "keep_original" : false,
                    "limit_first_letter_length" : 16,
                    "lowercase" : true,
                    "trim_whitespace" : true,
                    "keep_none_chinese_in_first_letter" : true
                }
            }
        }
    }
}
````

# 创建MAPPING

POST /user/user/_mapping 
````json
{
  "user": {
    "properties": {
      "name": {
        "type":"text",
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word",
        "boost": 8,
        "fields": {
          "title":{
                "type": "text",
                "analyzer": "ik_max_word"
          },
          "pinyin": {
              "type": "text",
              "store": false,
              "term_vector": "with_offsets",
              "analyzer": "pinyin_analyzer",
              "boost": 1
          }
        }
      },
      "id":{
        "type":"keyword"
      }
    }
  }
}
````