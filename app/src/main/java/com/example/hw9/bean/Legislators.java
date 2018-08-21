package com.example.hw9.bean;


import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

public class Legislators {


    private int count;
    private PageBean page;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class PageBean {

        private int count;
        private int per_page;
        private int page;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }
    }

    @Table(name="Legislators")
    public static class ResultsBean implements Serializable{

        private long id;
        private String bioguide_id;
        private String birthday;
        private String chamber;
        private String contact_form;
        private String crp_id;
        private int district;
        private String fax;
        private String first_name;
        private String gender;
        private String govtrack_id;
        private boolean in_office;
        private String last_name;
        private String leadership_role;
        private String middle_name;
        private String name_suffix;
        private String nickname;
        private String oc_email;
        private String ocd_id;
        private String office;
        private String party;
        private String phone;
        private String state;
        private String state_name;
        private String term_end;
        private String term_start;
        private String thomas_id;
        private String title;
        private String website;
        private int votesmart_id;
        private String facebook_id;
        private String twitter_id;
        private String lis_id;
        private int senate_class;
        private String state_rank;
        private List<String> fec_ids;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBioguide_id() {
            return bioguide_id;
        }

        public void setBioguide_id(String bioguide_id) {
            this.bioguide_id = bioguide_id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getChamber() {
            return chamber;
        }

        public void setChamber(String chamber) {
            this.chamber = chamber;
        }

        public String getContact_form() {
            return contact_form;
        }

        public void setContact_form(String contact_form) {
            this.contact_form = contact_form;
        }

        public String getCrp_id() {
            return crp_id;
        }

        public void setCrp_id(String crp_id) {
            this.crp_id = crp_id;
        }

        public int getDistrict() {
            return district;
        }

        public void setDistrict(int district) {
            this.district = district;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGovtrack_id() {
            return govtrack_id;
        }

        public void setGovtrack_id(String govtrack_id) {
            this.govtrack_id = govtrack_id;
        }

        public boolean isIn_office() {
            return in_office;
        }

        public void setIn_office(boolean in_office) {
            this.in_office = in_office;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getLeadership_role() {
            return leadership_role;
        }

        public void setLeadership_role(String leadership_role) {
            this.leadership_role = leadership_role;
        }

        public String getMiddle_name() {
            return middle_name;
        }

        public void setMiddle_name(String middle_name) {
            this.middle_name = middle_name;
        }

        public String getName_suffix() {
            return name_suffix;
        }

        public void setName_suffix(String name_suffix) {
            this.name_suffix = name_suffix;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOc_email() {
            return oc_email;
        }

        public void setOc_email(String oc_email) {
            this.oc_email = oc_email;
        }

        public String getOcd_id() {
            return ocd_id;
        }

        public void setOcd_id(String ocd_id) {
            this.ocd_id = ocd_id;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getParty() {
            return party;
        }

        public void setParty(String party) {
            this.party = party;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState_name() {
            return state_name;
        }

        public void setState_name(String state_name) {
            this.state_name = state_name;
        }

        public String getTerm_end() {
            return term_end;
        }

        public void setTerm_end(String term_end) {
            this.term_end = term_end;
        }

        public String getTerm_start() {
            return term_start;
        }

        public void setTerm_start(String term_start) {
            this.term_start = term_start;
        }

        public String getThomas_id() {
            return thomas_id;
        }

        public void setThomas_id(String thomas_id) {
            this.thomas_id = thomas_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public int getVotesmart_id() {
            return votesmart_id;
        }

        public void setVotesmart_id(int votesmart_id) {
            this.votesmart_id = votesmart_id;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public String getTwitter_id() {
            return twitter_id;
        }

        public void setTwitter_id(String twitter_id) {
            this.twitter_id = twitter_id;
        }

        public String getLis_id() {
            return lis_id;
        }

        public void setLis_id(String lis_id) {
            this.lis_id = lis_id;
        }

        public int getSenate_class() {
            return senate_class;
        }

        public void setSenate_class(int senate_class) {
            this.senate_class = senate_class;
        }

        public String getState_rank() {
            return state_rank;
        }

        public void setState_rank(String state_rank) {
            this.state_rank = state_rank;
        }

        public List<String> getFec_ids() {
            return fec_ids;
        }

        public void setFec_ids(List<String> fec_ids) {
            this.fec_ids = fec_ids;
        }

    }
}
