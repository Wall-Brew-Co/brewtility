
MAKE = make

# These are the locations of the directories we'll use
TARGET_DIR = target

#
# These are the main targets that we'll be making
#
version/major:
	$(info Updating major version and adding CHANGELOG entry...)
	@ lein vcs assert-committed
	@ lein change version leiningen.release/bump-version major
	@ lein change version leiningen.release/bump-version release
	@ lein sealog bump major
	@ lein pom
	@ npm version major --no-git-tag-version

version/minor:
	$(info Updating minor version and adding CHANGELOG entry...)
	@ lein vcs assert-committed
	@ lein change version leiningen.release/bump-version minor
	@ lein change version leiningen.release/bump-version release
	@ lein sealog bump minor
	@ lein pom
	@ npm version minor --no-git-tag-version

version/patch:
	$(info Updating patch version and adding CHANGELOG entry...)
	@ lein vcs assert-committed
	@ lein change version leiningen.release/bump-version patch
	@ lein change version leiningen.release/bump-version release
	@ lein sealog bump patch
	@ lein pom
	@ npm version patch --no-git-tag-version

changelog/render:
	$(info Rendering CHANGELOG...)
	@ lein sealog render
