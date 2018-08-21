package com.example.hw9.bean;


import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

public class Bills {


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

    @Table(name="Bills")
    public static class ResultsBean implements Comparable<ResultsBean>, Serializable {

        private long id;
        private String bill_id;
        private String bill_type;
        private String chamber;
        private int congress;
        private int cosponsors_count;
        private String enacted_as;
        private HistoryBean history;
        private String introduced_on;
        private String last_action_at;
        private String last_vote_at;
        private int number;
        private String official_title;
        private String popular_title;
        private String short_title;
        private SponsorBean sponsor;
        private String sponsor_id;
        private UrlsBean urls;
        private int withdrawn_cosponsors_count;
        private LastVersionBean last_version;
        private String last_version_on;
        private List<String> committee_ids;
        private List<String> related_bill_ids;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBill_id() {
            return bill_id;
        }

        public void setBill_id(String bill_id) {
            this.bill_id = bill_id;
        }

        public String getBill_type() {
            return bill_type;
        }

        public void setBill_type(String bill_type) {
            this.bill_type = bill_type;
        }

        public String getChamber() {
            return chamber;
        }

        public void setChamber(String chamber) {
            this.chamber = chamber;
        }

        public int getCongress() {
            return congress;
        }

        public void setCongress(int congress) {
            this.congress = congress;
        }

        public int getCosponsors_count() {
            return cosponsors_count;
        }

        public void setCosponsors_count(int cosponsors_count) {
            this.cosponsors_count = cosponsors_count;
        }

        public String getEnacted_as() {
            return enacted_as;
        }

        public void setEnacted_as(String enacted_as) {
            this.enacted_as = enacted_as;
        }

        public HistoryBean getHistory() {
            return history;
        }

        public void setHistory(HistoryBean history) {
            this.history = history;
        }

        public String getIntroduced_on() {
            return introduced_on;
        }

        public void setIntroduced_on(String introduced_on) {
            this.introduced_on = introduced_on;
        }

        public String getLast_action_at() {
            return last_action_at;
        }

        public void setLast_action_at(String last_action_at) {
            this.last_action_at = last_action_at;
        }

        public String getLast_vote_at() {
            return last_vote_at;
        }

        public void setLast_vote_at(String last_vote_at) {
            this.last_vote_at = last_vote_at;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getOfficial_title() {
            return official_title;
        }

        public void setOfficial_title(String official_title) {
            this.official_title = official_title;
        }

        public String getPopular_title() {
            return popular_title;
        }

        public void setPopular_title(String popular_title) {
            this.popular_title = popular_title;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public SponsorBean getSponsor() {
            return sponsor;
        }

        public void setSponsor(SponsorBean sponsor) {
            this.sponsor = sponsor;
        }

        public String getSponsor_id() {
            return sponsor_id;
        }

        public void setSponsor_id(String sponsor_id) {
            this.sponsor_id = sponsor_id;
        }

        public UrlsBean getUrls() {
            return urls;
        }

        public void setUrls(UrlsBean urls) {
            this.urls = urls;
        }

        public int getWithdrawn_cosponsors_count() {
            return withdrawn_cosponsors_count;
        }

        public void setWithdrawn_cosponsors_count(int withdrawn_cosponsors_count) {
            this.withdrawn_cosponsors_count = withdrawn_cosponsors_count;
        }

        public LastVersionBean getLast_version() {
            return last_version;
        }

        public void setLast_version(LastVersionBean last_version) {
            this.last_version = last_version;
        }

        public String getLast_version_on() {
            return last_version_on;
        }

        public void setLast_version_on(String last_version_on) {
            this.last_version_on = last_version_on;
        }

        public List<String> getCommittee_ids() {
            return committee_ids;
        }

        public void setCommittee_ids(List<String> committee_ids) {
            this.committee_ids = committee_ids;
        }

        public List<String> getRelated_bill_ids() {
            return related_bill_ids;
        }

        public void setRelated_bill_ids(List<String> related_bill_ids) {
            this.related_bill_ids = related_bill_ids;
        }

        @Override
        public int compareTo(ResultsBean resultsBean) {
            return -resultsBean.getIntroduced_on().compareTo(introduced_on);
        }

        public static class HistoryBean implements Serializable {

            private boolean active;
            private boolean awaiting_signature;
            private boolean enacted;
            private boolean vetoed;

            public boolean isActive() {
                return active;
            }

            public void setActive(boolean active) {
                this.active = active;
            }

            public boolean isAwaiting_signature() {
                return awaiting_signature;
            }

            public void setAwaiting_signature(boolean awaiting_signature) {
                this.awaiting_signature = awaiting_signature;
            }

            public boolean isEnacted() {
                return enacted;
            }

            public void setEnacted(boolean enacted) {
                this.enacted = enacted;
            }

            public boolean isVetoed() {
                return vetoed;
            }

            public void setVetoed(boolean vetoed) {
                this.vetoed = vetoed;
            }
        }

        public static class SponsorBean implements Serializable {

            private String first_name;
            private String last_name;
            private String middle_name;
            private String name_suffix;
            private String nickname;
            private String title;

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class UrlsBean implements Serializable {

            private String congress;
            private String govtrack;
            private String opencongress;

            public String getCongress() {
                return congress;
            }

            public void setCongress(String congress) {
                this.congress = congress;
            }

            public String getGovtrack() {
                return govtrack;
            }

            public void setGovtrack(String govtrack) {
                this.govtrack = govtrack;
            }

            public String getOpencongress() {
                return opencongress;
            }

            public void setOpencongress(String opencongress) {
                this.opencongress = opencongress;
            }
        }

        public static class LastVersionBean implements Serializable {

            private String version_code;
            private String issued_on;
            private String version_name;
            private String bill_version_id;
            private UrlsBeanX urls;
            private int pages;

            public String getVersion_code() {
                return version_code;
            }

            public void setVersion_code(String version_code) {
                this.version_code = version_code;
            }

            public String getIssued_on() {
                return issued_on;
            }

            public void setIssued_on(String issued_on) {
                this.issued_on = issued_on;
            }

            public String getVersion_name() {
                return version_name;
            }

            public void setVersion_name(String version_name) {
                this.version_name = version_name;
            }

            public String getBill_version_id() {
                return bill_version_id;
            }

            public void setBill_version_id(String bill_version_id) {
                this.bill_version_id = bill_version_id;
            }

            public UrlsBeanX getUrls() {
                return urls;
            }

            public void setUrls(UrlsBeanX urls) {
                this.urls = urls;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public static class UrlsBeanX implements Serializable {

                private String html;
                private String pdf;
                private String xml;

                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }

                public String getPdf() {
                    return pdf;
                }

                public void setPdf(String pdf) {
                    this.pdf = pdf;
                }

                public String getXml() {
                    return xml;
                }

                public void setXml(String xml) {
                    this.xml = xml;
                }
            }
        }
    }
}
