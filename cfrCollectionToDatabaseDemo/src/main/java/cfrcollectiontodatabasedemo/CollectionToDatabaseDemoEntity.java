package cfrcollectiontodatabasedemo;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "collection_to_database_entity")
public class CollectionToDatabaseDemoEntity {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(name = "str_list")
    private ArrayList<String> strList;

    // @Column(name = "long_str")
    // private Set<Long> longSet;

    // @Column(name = "string_date_map")
    // private Map<String, Date> stringDateMap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public List<String> getStrList() {
    //     return strList;
    // }
    //
    // public void setStrList(List<String> strList) {
    //     this.strList = strList;
    // }

    // public Set<Long> getLongSet() {
    //     return longSet;
    // }
    //
    // public void setLongSet(Set<Long> longSet) {
    //     this.longSet = longSet;
    // }

    // public Map<String, Date> getStringDateMap() {
    //     return stringDateMap;
    // }
    //
    // public void setStringDateMap(Map<String, Date> stringDateMap) {
    //     this.stringDateMap = stringDateMap;
    // }

    public ArrayList<String> getStrList() {
        return strList;
    }

    public void setStrList(ArrayList<String> strList) {
        this.strList = strList;
    }
}
