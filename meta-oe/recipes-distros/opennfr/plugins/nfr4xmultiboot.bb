SUMMARY = "Multi boot loader for enigma2"
MAINTAINER = "oe-alliance"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r9"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "freetype"

SRC_URI = "git://github.com/carlo0815/bootmanger.git;protocol=https"

inherit autotools-brokensep pkgconfig

S = "${WORKDIR}/git"

EXTRA_OEMAKE = " \
    'CFLAGS=${CFLAGS} \
    -I=${includedir}/freetype2 \
    ${@bb.utils.contains("MACHINE_FEATURES", "singlecore", "-Dnfr4x_DEFAULT_TIMER=10" , "-Dnfr4x_DEFAULT_TIMER=5", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "-Dnfr4x_HAVE_TEXTLCD" , "", d)} \
    ${@bb.utils.contains("IMAGE_FSTYPES", "ubi", "-Dnfr4x_FLASH_UBI" , "", d)} \
    ${@bb.utils.contains("IMAGE_FSTYPES", "jffs2", "-Dnfr4x_FLASH_JFFS2" , "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "-Dnfr4x_DREAMBOX", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "mmc", "-Dnfr4x_MMCBLK", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "emmc", "-Dnfr4x_MMCBLK", "", d)} \   
    -Dnfr4x_KERNEL_MTD=\"/dev/${MTD_KERNEL}\"' \
    'LDFLAGS= -lfreetype ${LDFLAGS}' \
    "

do_install() {
    install -d ${D}/sbin
    install -m 755 ${S}/src/nfr4x_multiboot ${D}/sbin
    install -m 644 ${S}/contrib/nfr4x-multiboot-branding-helper.py ${D}/sbin
}
