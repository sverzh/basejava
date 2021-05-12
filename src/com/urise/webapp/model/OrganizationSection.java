package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {
    private final static long serialVersionUID = 1L;
    private List<Organization> organizations = new ArrayList<>();

    public OrganizationSection() {
    }
    public OrganizationSection(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(Organization organization) {
        if (OrganizationExist(organization) == -1) {
            organizations.add(organization);
        } else {
            organizations.get(OrganizationExist(organization)).periodList.add(organization.periodList.get(0));
        }
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Organization periodSection : organizations) {
            builder.append(periodSection + "\n");
        }
        return builder.toString();
    }
}
