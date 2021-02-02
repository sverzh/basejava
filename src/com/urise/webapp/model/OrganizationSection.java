package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection {
    protected List<Organization> organizations = new ArrayList<>();

    public void addOrganization(Organization organization) {
        if (OrganizationExist(organization) == -1) {
            organizations.add(organization);
        } else organizations.get(OrganizationExist(organization)).periodList.add(organization.period);
    }

    private int OrganizationExist(Organization organization) {
        for (int i = 0; i < organizations.size(); i++) {
            if (organization.getOrganization().equals(organizations.get(i).getOrganization())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Organization periodSection : organizations
        ) {
            builder.append(periodSection + "\n");
        }
        return builder.toString();
    }
}
