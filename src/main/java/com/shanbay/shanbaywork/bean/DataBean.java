package com.shanbay.shanbaywork.bean;

/**
 * Created by 明月春秋 on 2017/12/3.
 * 扇贝单词中数据的信息Bean
 * 继承自Bean基类
 */

public class DataBean extends Bean {

    private EnDefinitions en_definitions;//返回英文释义的数组

    private CnDefinition cn_definition;//中文释义

    private int id;//单词的id

    private int retention;//单词的熟悉度

    private String definition;//中文释义

    private int target_retention;//用户自定义的目标学习度

    private long learning_id;//learing id，如果未返回，在表明用户没学过该单词

    private String content;//查询的单词

    private String pronunciation;//单词的音标

    private EnDefinition en_definition;//英文释义

    private String audio;

    private String uk_audio;

    private String us_audio;

    private boolean has_audio;

    private int conent_id;

    private String audio_name;

    private int num_sense;

    private String content_type;

    private int sense_id;

    private boolean has_collins_defn;

    private String has_oxford_defn;

    private String url;

    private int object_id;

    private String pron;

    private Pronunciations pronunciations;

    private AudioAddresses audio_addresses;

    public EnDefinitions getEn_definitions() {
        return en_definitions;
    }

    public void setEn_definitions(EnDefinitions en_definitions) {
        this.en_definitions = en_definitions;
    }

    public CnDefinition getCn_definition() {
        return cn_definition;
    }

    public void setCn_definition(CnDefinition cn_definition) {
        this.cn_definition = cn_definition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getTarget_retention() {
        return target_retention;
    }

    public void setTarget_retention(int target_retention) {
        this.target_retention = target_retention;
    }

    public long getLearning_id() {
        return learning_id;
    }

    public void setLearning_id(long learning_id) {
        this.learning_id = learning_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public EnDefinition getEn_definition() {
        return en_definition;
    }

    public void setEn_definition(EnDefinition en_definition) {
        this.en_definition = en_definition;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getUk_audio() {
        return uk_audio;
    }

    public void setUk_audio(String uk_audio) {
        this.uk_audio = uk_audio;
    }

    public String getUs_audio() {
        return us_audio;
    }

    public void setUs_audio(String us_audio) {
        this.us_audio = us_audio;
    }

    public boolean isHas_audio() {
        return has_audio;
    }

    public void setHas_audio(boolean has_audio) {
        this.has_audio = has_audio;
    }

    public int getConent_id() {
        return conent_id;
    }

    public void setConent_id(int conent_id) {
        this.conent_id = conent_id;
    }

    public String getAudio_name() {
        return audio_name;
    }

    public void setAudio_name(String audio_name) {
        this.audio_name = audio_name;
    }

    public int getNum_sense() {
        return num_sense;
    }

    public void setNum_sense(int num_sense) {
        this.num_sense = num_sense;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public int getSense_id() {
        return sense_id;
    }

    public void setSense_id(int sense_id) {
        this.sense_id = sense_id;
    }

    public boolean isHas_collins_defn() {
        return has_collins_defn;
    }

    public void setHas_collins_defn(boolean has_collins_defn) {
        this.has_collins_defn = has_collins_defn;
    }

    public String getHas_oxford_defn() {
        return has_oxford_defn;
    }

    public void setHas_oxford_defn(String has_oxford_defn) {
        this.has_oxford_defn = has_oxford_defn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public Pronunciations getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(Pronunciations pronunciations) {
        this.pronunciations = pronunciations;
    }

    public AudioAddresses getAudio_addresses() {
        return audio_addresses;
    }

    public void setAudio_addresses(AudioAddresses audio_addresses) {
        this.audio_addresses = audio_addresses;
    }
}
