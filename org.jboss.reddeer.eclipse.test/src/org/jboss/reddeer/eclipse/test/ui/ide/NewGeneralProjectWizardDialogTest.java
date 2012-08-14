package org.jboss.reddeer.eclipse.test.ui.ide;

import static org.junit.Assert.assertTrue;

import org.jboss.reddeer.eclipse.jdt.ui.packageexplorer.PackageExplorer;
import org.jboss.reddeer.eclipse.ui.ide.NewGeneralProjectReferencesWizardPage;
import org.jboss.reddeer.eclipse.ui.ide.NewGeneralProjectWizardDialog;
import org.jboss.reddeer.eclipse.ui.ide.NewGeneralProjectWizardPage;
import org.jboss.reddeer.swt.exception.WidgetNotEnabledException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NewGeneralProjectWizardDialogTest {
  
  private static final String DEFAULT_PROJECT_NAME = "defaultGeneralProject";
  private static final String CUSTOMIZED_PROJECT_NAME = "customizedGeneralProject";
  private PackageExplorer packageExplorer;
  @Before
  public void setUp() {
    packageExplorer = new PackageExplorer();
  }
	@Test
	public void createGeneralProjects(){
	  // create defult project
		NewGeneralProjectWizardDialog wizardDialog = 
		    new NewGeneralProjectWizardDialog();
		wizardDialog.open();
		NewGeneralProjectWizardPage projectPage =
		    new NewGeneralProjectWizardPage(wizardDialog);
		projectPage.setProjectName(NewGeneralProjectWizardDialogTest.DEFAULT_PROJECT_NAME);
		wizardDialog.finish();
		assertTrue("Package Explorer has to contain project " +
		    NewGeneralProjectWizardDialogTest.DEFAULT_PROJECT_NAME +
		    " but it doesn't",
		  packageExplorer.contains(NewGeneralProjectWizardDialogTest.DEFAULT_PROJECT_NAME));
		// create customized project
    wizardDialog.open();
    NewGeneralProjectReferencesWizardPage projectReferencesPage =
        new NewGeneralProjectReferencesWizardPage(wizardDialog);
    projectPage.setProjectName(NewGeneralProjectWizardDialogTest.CUSTOMIZED_PROJECT_NAME);
    final String tmpDir = System.getProperty("java.io.tmpdir");
    projectPage.setProjectLocation(tmpDir);
    try{
      projectPage.addProjectToWorkingSet("dummyws");  
    }catch (WidgetNotEnabledException wnee){
      // do nothing this exception means there is no Working set
      // defined but all widgets were found
    }    
    projectReferencesPage.setProjectReferences(NewGeneralProjectWizardDialogTest.DEFAULT_PROJECT_NAME);
    wizardDialog.finish();
    assertTrue("Package Explorer has to contain project " +
        NewGeneralProjectWizardDialogTest.CUSTOMIZED_PROJECT_NAME +
        " but it doesn't",
      packageExplorer.contains(NewGeneralProjectWizardDialogTest.CUSTOMIZED_PROJECT_NAME));
  }
	@After
	public void deleteProjects(){
	  packageExplorer.deleteItem(NewGeneralProjectWizardDialogTest.CUSTOMIZED_PROJECT_NAME,
	      true);
	  packageExplorer.deleteItem(NewGeneralProjectWizardDialogTest.DEFAULT_PROJECT_NAME,
	      true);
	}
	
}
