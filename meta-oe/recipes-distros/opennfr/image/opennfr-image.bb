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


rootfs_postprocess() {
    curdir=$PWD
    cd ${IMAGE_ROOTFS}
    # because we're so used to it
    ln -s opkg usr/bin/ipkg || true
    ln -s opkg-cl usr/bin/ipkg-cl || true
    cd $curdir
    set -x

    if [ ! -z "${DEPLOY_KEEP_PACKAGES}" ]; then
        return
    fi

}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_postprocess; "

