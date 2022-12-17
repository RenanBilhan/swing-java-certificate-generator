package generator;

import lombok.Builder;

@Builder
public class CertificateDto {
    public CertificateDto(){
        super();
    }

    public CertificateDto(String name, String workLoad, String course) {
        this.name = name;
        this.workLoad = workLoad;
        this.course = course;
    }


    private String name;


    private String workLoad;


    private String course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(String workLoad) {
        this.workLoad = workLoad;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}


