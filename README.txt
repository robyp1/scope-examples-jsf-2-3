*scope in view*: � solo di JSF, la lista rimane valorizzata (il bean non viene reinstanziato
 a meno che non si ricarica la pagina con F5 oppure si invia con post che qui non c�)

*scope in Conversational*: � solo di CDI: la lista rimane sempre nell'ultimo stato, il bean
viene distrutto solo quando viene chiamato il destroy chiama Conversation.end



Here's the manual procedure:

Extract javax.faces.jar with a ZIP tool. You'll get 3 folders com, javax and META-INF.

Pack com and META-INF folders into jsf-impl.jar with a ZIP tool.

Then, delete all files/subfolders in META-INF except of MANIFEST.MF.

Pack javax and META-INF folders into jsf-api.jar with a ZIP tool.

Continue here with those JARs: Upgrade JSF / Mojarra in JBoss AS / EAP / WildFly (see ****continue above).

For the interested, JBoss AS and WildFly has internally a modular separation of 
Java EE based API and impl files. The separated JAR files jsf-api.jar and jsf-impl.jar are still needed. 
The reason is not really technical, but just an extra service to force developers programming against 
the right libraries. Only the API modules are exposed during compile time (usually, 
via the IDE-integrated plugin which adds them to "build path"). This should avoid starters 
accidentally finding, importing and using implementation classes such as those in com.sun.faces.
* package.

Already since version 1.x, the JSF implementation Mojarra was composed of two JAR files: 
jsf-api.jar and jsf-impl.jar. The API JAR contained the javax.faces.* classes and the 
implementation JAR contained the com.sun.faces.* classes. 
Since the change of the build system conform Java EE Maven rules, both the API and implementation 
classes were merged into a single javax.faces.jar file, see also issue 2028 (started with Mojarra 2.1.6 
at Dec 2011). Since Mojarra 2.3, the separated JAR files are not built anymore.

***** continue
The below procedure applies to JBoss AS 7.2+, JBoss EAP 6.1+, and JBoss WildFly 8+ and assumes that you've full control over the server installation and configuration. This upgrades the server-wide default JSF version:

Download the individual Mojarra API and impl files (and thus not the single javax.faces.jar file). Current latest 2.1.x version is 2.1.29 and current latest 2.2.x version is 2.2.14. Let's assume that you want to upgrade to 2.2.x. You can download them individually from their Maven repository:
jsf-api-2.2.14.jar
jsf-impl-2.2.14.jar
Make sure that JBoss is shutdown.
Update JSF API in /modules/system/layers/base/javax/faces/api/main:
Delete or backup the old JAR file (do NOT keep it in the same folder, even not renamed!).
Put jsf-api-2.2.14.jar file in there.
Open module.xml file and edit <resource-root> to specify the new file name as in <resource-root path="jsf-api-2.2.14.jar"/>
Update JSF impl in /modules/system/layers/base/com/sun/jsf-impl/main:
Delete or backup the old JAR file (do NOT keep it in the same folder, even not renamed!).
Put jsf-impl-2.2.14.jar file in there.
Open module.xml file and edit <resource-root> to specify the new file name as in <resource-root path="jsf-impl-2.2.14.jar"/>
Cleanup JBoss cache/work data just to make sure that there's no old copy of the JARs from previous deployments hanging in there which would potentially only collide with the new JARs:
Trash all contents of /standalone/data (except of custom data folders like folder containing uploaded files, of course)
Trash all contents of /standalone/deployments
Trash all contents of /standalone/tmp
Start JBoss. It should now use the new JSF version for all deployments.
The same procedure applies to JBoss AS 7.0/7.1 and JBoss EAP 6.0, you only need to browse in /modules/* instead of /modules/system/layers/base/*, and you need to explicitly delete the old .index file there, if any (JBoss will autocreate one). Also, if the module.xml in API folder misses <module name="com.sun.jsf-impl"/> inside <dependencies>, then you need to manually add it.

Important note is that Mojarra 2.2.x versions older than 2.2.7 will fail in AS/EAP during deployment with the following exception: org.jboss.weld.context.ContextNotActiveException: WELD-001303 No active contexts for scope type javax.faces.flow.builder.FlowDefinition. You've then basically 2 options: downgrade to Mojarra 2.1.x, or upgrade to at least 2.2.7 or newer.

In case you'd like to upgrade to Mojarra 2.3, which doesn't offer a 2-JAR variant anymore on Maven, you'd need to manually create the 2-JAR variant based on javax.faces.jar file as per this procedure: How to install one jar variant of JSF (javax.faces.jar) on WildFly.