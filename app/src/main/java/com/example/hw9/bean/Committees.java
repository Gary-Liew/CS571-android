package com.example.hw9.bean;


import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

public class Committees {

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
        private String per_page;
        private String page;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }
    }

    @Table(name="Committees")
    public static class ResultsBean implements Serializable{

        private long id;
        private String chamber;
        private String committee_id;
        private String name;
        private String parent_committee_id;
        private boolean subcommittee;
        private String office;
        private String phone;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getChamber() {
            return chamber;
        }

        public void setChamber(String chamber) {
            this.chamber = chamber;
        }

        public String getCommittee_id() {
            return committee_id;
        }

        public void setCommittee_id(String committee_id) {
            this.committee_id = committee_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent_committee_id() {
            return parent_committee_id;
        }

        public void setParent_committee_id(String parent_committee_id) {
            this.parent_committee_id = parent_committee_id;
        }

        public boolean isSubcommittee() {
            return subcommittee;
        }

        public void setSubcommittee(boolean subcommittee) {
            this.subcommittee = subcommittee;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
