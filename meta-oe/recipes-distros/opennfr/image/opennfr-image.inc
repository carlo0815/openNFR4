SUMMARY = "OPENNFR Image"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
MAINTAINER = "OPENNFR team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "${BUILD_VERSION}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_rootfs[deptask] = "do_rm_work"

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

do_package_index[nostamp] = "1"
do_package_index[depends] += "${PACKAGEINDEXDEPS}"

rootfs_postprocess() {
    curdir=$PWD
    cd ${IMAGE_ROOTFS}
    # because we're so used to it
    ln -s opkg usr/bin/ipkg || true
    ln -s opkg-cl usr/bin/ipkg-cl || true
    ln -s libcrypto.so.1.0.2 usr/lib/libcrypto.so.0.9.8 || true
    cd $curdir
    set -x


    if [ ! -z "${DEPLOY_KEEP_PACKAGES}" ]; then
        return
    fi

    packagedirs="${DEPLOY_DIR_IPK}"
    for arch in $ipkgarchs; do
        packagedirs="$packagedirs ${DEPLOY_DIR_IPK}/$arch"
    done

    multilib_archs="${MULTILIB_ARCHS}"
    for arch in $multilib_archs; do
        packagedirs="$packagedirs ${DEPLOY_DIR_IPK}/$arch"
    done

    for pkgdir in $packagedirs; do
        if [ -e $pkgdir/ ]; then
            for i in ${unused}; do
                rm -f $pkgdir/$i;
            done;
        fi
    done
    
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_postprocess; "
addtask do_package_index after do_rootfs before do_image_complete

