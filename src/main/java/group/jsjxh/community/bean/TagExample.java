package group.jsjxh.community.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TagExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public TagExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTnoIsNull() {
            addCriterion("TNO is null");
            return (Criteria) this;
        }

        public Criteria andTnoIsNotNull() {
            addCriterion("TNO is not null");
            return (Criteria) this;
        }

        public Criteria andTnoEqualTo(Integer value) {
            addCriterion("TNO =", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotEqualTo(Integer value) {
            addCriterion("TNO <>", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoGreaterThan(Integer value) {
            addCriterion("TNO >", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("TNO >=", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLessThan(Integer value) {
            addCriterion("TNO <", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoLessThanOrEqualTo(Integer value) {
            addCriterion("TNO <=", value, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoIn(List<Integer> values) {
            addCriterion("TNO in", values, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotIn(List<Integer> values) {
            addCriterion("TNO not in", values, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoBetween(Integer value1, Integer value2) {
            addCriterion("TNO between", value1, value2, "tno");
            return (Criteria) this;
        }

        public Criteria andTnoNotBetween(Integer value1, Integer value2) {
            addCriterion("TNO not between", value1, value2, "tno");
            return (Criteria) this;
        }

        public Criteria andTnameIsNull() {
            addCriterion("TNAME is null");
            return (Criteria) this;
        }

        public Criteria andTnameIsNotNull() {
            addCriterion("TNAME is not null");
            return (Criteria) this;
        }

        public Criteria andTnameEqualTo(String value) {
            addCriterion("TNAME =", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotEqualTo(String value) {
            addCriterion("TNAME <>", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameGreaterThan(String value) {
            addCriterion("TNAME >", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameGreaterThanOrEqualTo(String value) {
            addCriterion("TNAME >=", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameLessThan(String value) {
            addCriterion("TNAME <", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameLessThanOrEqualTo(String value) {
            addCriterion("TNAME <=", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameLike(String value) {
            addCriterion("TNAME like", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotLike(String value) {
            addCriterion("TNAME not like", value, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameIn(List<String> values) {
            addCriterion("TNAME in", values, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotIn(List<String> values) {
            addCriterion("TNAME not in", values, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameBetween(String value1, String value2) {
            addCriterion("TNAME between", value1, value2, "tname");
            return (Criteria) this;
        }

        public Criteria andTnameNotBetween(String value1, String value2) {
            addCriterion("TNAME not between", value1, value2, "tname");
            return (Criteria) this;
        }

        public Criteria andTcreateAtIsNull() {
            addCriterion("TCREATE_AT is null");
            return (Criteria) this;
        }

        public Criteria andTcreateAtIsNotNull() {
            addCriterion("TCREATE_AT is not null");
            return (Criteria) this;
        }

        public Criteria andTcreateAtEqualTo(Date value) {
            addCriterion("TCREATE_AT =", value, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtNotEqualTo(Date value) {
            addCriterion("TCREATE_AT <>", value, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtGreaterThan(Date value) {
            addCriterion("TCREATE_AT >", value, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("TCREATE_AT >=", value, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtLessThan(Date value) {
            addCriterion("TCREATE_AT <", value, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtLessThanOrEqualTo(Date value) {
            addCriterion("TCREATE_AT <=", value, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtIn(List<Date> values) {
            addCriterion("TCREATE_AT in", values, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtNotIn(List<Date> values) {
            addCriterion("TCREATE_AT not in", values, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtBetween(Date value1, Date value2) {
            addCriterion("TCREATE_AT between", value1, value2, "tcreateAt");
            return (Criteria) this;
        }

        public Criteria andTcreateAtNotBetween(Date value1, Date value2) {
            addCriterion("TCREATE_AT not between", value1, value2, "tcreateAt");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TB_TAG
     *
     * @mbg.generated do_not_delete_during_merge Sat Nov 23 12:36:46 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TB_TAG
     *
     * @mbg.generated Sat Nov 23 12:36:46 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}