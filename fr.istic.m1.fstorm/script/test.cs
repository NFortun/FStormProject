# This example demonstrates automatic loop fusion + array contraction 
# for a moderately complex image processing kernel (Harris corner detection) 

debug(1);

name = "readcomponents";
proj = CreateGecosProject(name);
AddSourceToGecosProject(proj,"./test.c");
CDTFrontend(proj);
pragmas = FindAllPragmas(proj);
list=ReadComponents(pragmas);
Test(list);
