This is the common dao layer for OOPMS project.
Run "ant dist" to compile and pack the component.

Version 0.0.5
===============
- Add method getRights(long) to get rights of user in WorkUnit (Organization, Group, Project).
- Update ProjectDao to support get information of projects with given user id.
Refer:
- http://open-ones.googlecode.com/svn/trunk/ProjectList/iPMS/SourceCode/FsoftInsight/src/com/fms1/common/Project.java,
  + method Vector getProjectsByWUs(Vector projectRoles)
Version 0.0.4
===============
- Skip hibernate configuration files, log4j configuration from generated package.
- Add entities: Tasks
- Add RequirementDao

Version 0.0.3
===============
- Add DeveloperDao

Version 0.0.2
- Update package name (openones.tms.dms...) to openones.oopms... in XML Hibernate mapping file
- Update suffix date format of generated package: yyyyMMddhhmmss 