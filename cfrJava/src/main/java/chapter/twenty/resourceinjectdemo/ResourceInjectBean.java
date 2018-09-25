package chapter.twenty.resourceinjectdemo;

import java.util.List;

import org.springframework.core.io.Resource;

public class ResourceInjectBean {
    private Resource resource;
    
    private List<Resource> resourceList;
    
    // 路径通配符
//    private List<Resource> resourcePathWildCard;
//    
//    public List<Resource> getResourcePathWildCard() {
//		return resourcePathWildCard;
//	}
//
//	public void setResourcePathWildCard(List<Resource> resourcePathWildCard) {
//		this.resourcePathWildCard = resourcePathWildCard;
//	}

	public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
}
