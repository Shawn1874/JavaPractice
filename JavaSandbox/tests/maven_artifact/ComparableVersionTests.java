package maven_artifact;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.maven.artifact.versioning.ComparableVersion;

class ComparableVersionTests {

	private static final ComparableVersion[] VERSIONS = new ComparableVersion[]{
	        new ComparableVersion("NotAVersionSting"),
	        new ComparableVersion("1.0a1-SNAPSHOT"),
	        new ComparableVersion("1.0-alpha1"),
	        new ComparableVersion("1.0beta1-SNAPSHOT"),
	        new ComparableVersion("1.0-b2"),
	        new ComparableVersion("1.0-beta3.SNAPSHOT"),
	        new ComparableVersion("1.0-beta3"),
	        new ComparableVersion("1.0-milestone1-SNAPSHOT"),
	        new ComparableVersion("1.0-m2"),
	        new ComparableVersion("1.0-rc1-SNAPSHOT"),
	        new ComparableVersion("1.0-cr1"),
	        new ComparableVersion("1.0-SNAPSHOT"),
	        new ComparableVersion("1.0"),
	        new ComparableVersion("1.0-ga"),
	        new ComparableVersion("1.0-sp"),
	        new ComparableVersion("1.0-a"),
	        new ComparableVersion("1.0-RELEASE"),
	        new ComparableVersion("1.0-whatever"),
	        new ComparableVersion("1.0.z"),
	        new ComparableVersion("1.0.1"),
	        new ComparableVersion("1.0.1.0.0.0.0.0.0.0.0.0.0.0.1")
	};
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	/**
	 * Remember that Arrays.sort is sorting an array of ComparableVersion objects, not
	 * strings.  This test verifies that the sort operation results in the same order
	 * as assumed when the original array was constructed.
	 * 
	 */
	public void ensureArrayInOrder() {
		ComparableVersion[] sortedArray = VERSIONS.clone();
	    Arrays.sort(sortedArray);
	    assertArrayEquals(VERSIONS, sortedArray);
	}
	
	@Test
	/**
	 * Test the shorthand aliases for each well known qualifier.
	 */
	public void testAliases() {
		var alpha = new ComparableVersion("1.0-alpha1");
		var a = new ComparableVersion("1.0-a1");
	    assertEquals(alpha, a);
	    assertEquals(new ComparableVersion("1.0-alpha1"), new ComparableVersion("1.0-a1"));
	    assertEquals(new ComparableVersion("1.0-beta1"), new ComparableVersion("1.0-b1"));
	    assertEquals(new ComparableVersion("1.0-milestone1"), new ComparableVersion("1.0-m1"));
	    assertEquals(new ComparableVersion("1.0-rc1"), new ComparableVersion("1.0-cr1"));
	}
	
	/**
	 * When transitioning from digit to qualifier the '-' is optional.
	 */
	@Test
	public void testSeparators() {
	    assertEquals(new ComparableVersion("1.0alpha1"), new ComparableVersion("1.0-a1"));
	    assertEquals(new ComparableVersion("1.0alpha-1"), new ComparableVersion("1.0-a1"));
	    assertEquals(new ComparableVersion("1.0beta1"), new ComparableVersion("1.0-b1"));
	    assertEquals(new ComparableVersion("1.0beta-1"), new ComparableVersion("1.0-b1"));
	    assertEquals(new ComparableVersion("1.0milestone1"), new ComparableVersion("1.0-m1"));
	    assertEquals(new ComparableVersion("1.0milestone-1"), new ComparableVersion("1.0-m1"));
	    assertEquals(new ComparableVersion("1.0rc1"), new ComparableVersion("1.0-cr1"));
	    assertEquals(new ComparableVersion("1.0rc-1"), new ComparableVersion("1.0-cr1"));
	    assertEquals(new ComparableVersion("1.0ga"), new ComparableVersion("1.0"));
	}
	
	@Test
	/**
	 * Confirms that final release qualifiers are optional.  An empty string is equivalent to one of these final qualifiers.
	 */
	public void testDifferentFinalReleases() {
	    assertEquals(new ComparableVersion("1.0-ga"), new ComparableVersion("1.0"));
	    assertEquals(new ComparableVersion("1.0-final"), new ComparableVersion("1.0"));
	}
	
	@Test
	/**
	 * Confirms that custom qualifiers are considered later releases than those with well known
	 * qualifiers.  
	 */
	public void testCustomQualifier() {
		var integration = new ComparableVersion("1.0-integration-SNAPSHOT");
		var release = new ComparableVersion("1.0-SNAPSHOT");
	    assertTrue(integration.compareTo(release) > 0 );
	    
		integration = new ComparableVersion("1.0-beta1-SNAPSHOT");
		release = new ComparableVersion("1.0-SNAPSHOT");
	    assertTrue(integration.compareTo(release) < 0 );
	    
		integration = new ComparableVersion("1.0-beta1-RELEASE");
		release = new ComparableVersion("1.0-beta1-SNAPSHOT");
	    assertTrue(integration.compareTo(release) > 0 );
	    
		integration = new ComparableVersion("1.0-beta1-SNAPSHOT");
		release = new ComparableVersion("1.0-beta1");
	    assertTrue(integration.compareTo(release) < 0 );
	    
		integration = new ComparableVersion("1.0-sp");
		release = new ComparableVersion("1.0-RELEASE");
	    assertTrue(integration.compareTo(release) < 0 );
	    
		integration = new ComparableVersion("1.0");
		release = new ComparableVersion("1.0-RELEASE");
	    assertTrue(integration.compareTo(release) < 0 );
	}
}
