SUMMARY = "OPENNFR Image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "OPENNFR team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "${BUILD_VERSION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_INSTALL = " \
    opennfr-base \
    packagegroup-base-smbfs-client \
    packagegroup-base-smbfs-server \
    packagegroup-base-nfs \ 
"

export IMAGE_BASENAME = "opennfr-image"
IMAGE_LINGUAS = ""

IMAGE_FEATURES += "package-management"	

inherit image
