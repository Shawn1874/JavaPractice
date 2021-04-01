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
	public void ensureArrayInOrder() {
		ComparableVersion[] sortedArray = VERSIONS.clone();
	    Arrays.sort(sortedArray);
	    assertArrayEquals(VERSIONS, sortedArray);
	}
}
