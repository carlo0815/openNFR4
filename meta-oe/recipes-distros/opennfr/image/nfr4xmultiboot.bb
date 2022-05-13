SUMMARY = "Multi boot loader for enigma2"
MAINTAINER = "oe-alliance"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "freetype"

SRC_URI = "git://github.com/carlo0815/bootmanger.git;protocol=https;branch=master"

inherit autotools-brokensep pkgconfig

S = "${WORKDIR}/git"

EXTRA_OEMAKE = " \
    'CFLAGS=${CFLAGS} \
    -I=${includedir}/freetype2 \
    ${@bb.utils.contains("MACHINE_FEATURES", "singlecore", "-DNFR4X_DEFAULT_TIMER=10" , "-DNFR4X_DEFAULT_TIMER=5", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "-DNFR4X_HAVE_TEXTLCD" , "", d)} \
    ${@bb.utils.contains("IMAGE_FSTYPES", "ubi", "-DNFR4X_FLASH_UBI" , "", d)} \
    ${@bb.utils.contains("IMAGE_FSTYPES", "jffs2", "-DNFR4X_FLASH_JFFS2" , "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "-DNFR4X_DREAMBOX", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "mmc", "-DNFR4X_MMCBLK", "", d)} \
    -DNFR4X_KERNEL_MTD=\"/dev/${MTD_KERNEL}\"' \
    'LDFLAGS= -lfreetype ${LDFLAGS}' \
    "

do_install() {
    install -d ${D}/sbin
    install -m 755 ${S}/src/nfr4x_multiboot ${D}/sbin
    install -m 644 ${S}/contrib/nfr4x-multiboot-branding-helper.py ${D}/sbin
}

pkg_preinst_${PN}() {
#!/bin/sh
if mountpoint -q /usr/lib/enigma2/python/Plugins/Extensions/NFR4XBoot; then
    echo "NFR4XBoot will only install on main image."
    echo "Child image is running - canceling installation!"
    sleep 3
    exit 1
else
    echo "Main image is running - proceeding installation..."
    exit 0
fi
}

pkg_postinst_${PN}() {
rm /sbin/init
ln -s /sbin/nfr4x_multiboot /sbin/init
}

pkg_postinst_${PN}_openbh() {
}

pkg_postrm_${PN}() {
rm /sbin/init
ln -s /sbin/init.sysvinit /sbin/init
} 
