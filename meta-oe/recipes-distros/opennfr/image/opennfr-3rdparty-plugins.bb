SUMMARY = "3rd Party plugins for Enigma2"
MAINTAINER = "OpenNFR Team"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=45de10587e108efb50c321c1affd5e00"

inherit gitpkgv autotools deploy

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r37"
SRC_URI="git://github.com/carlo0815/3rdparty-plugins.git;protocol=git;branch=4.4"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-boxtype=${MACHINEBUILD} \
"

ALLOW_EMPTY_${PN} = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

DEPENDS = "enigma2"

THIRDPARTY_MACHINE_PLUGINS_vusolo4k = " \
    enigma2-plugin-extensions-vmc_3.04R24_all.ipk \
    "    
THIRDPARTY_MACHINE_PLUGINS_e3hd = " \
    enigma2-plugin-extensions-hbbtv_2.13_E3HD_mips32el.ipk \
    "
THIRDPARTY_MACHINE_PLUGINS_odinm7 = " \
    enigma2-plugin-extensions-hbbtv_2.12_ODIN_mips32el.ipk \
    "
THIRDPARTY_MACHINE_PLUGINS_starsatlx = " \
    enigma2-plugin-extensions-hbbtv_2.12_ODIN_mips32el.ipk \
    "
THIRDPARTY_MACHINE_PLUGINS_classm = " \
    enigma2-plugin-extensions-hbbtv_2.12_ODIN_mips32el.ipk \
    "
THIRDPARTY_MACHINE_PLUGINS_axodin = " \
    enigma2-plugin-extensions-hbbtv_2.12_ODIN_mips32el.ipk \
    "
THIRDPARTY_MACHINE_PLUGINS_axase3 = " \
    enigma2-plugin-extensions-hbbtv_2.12_ODIN_mips32el.ipk \
    "
do_install() {
}

do_deploy() {
}


do_deploy_append_mipsel() {    
    install -d 0755 ${DEPLOY_DIR_IPK}/3rdparty
    install -d 0755 ${DEPLOY_DIR_IPK}/${MACHINE}_3rdparty
    install -m 0644 ${S}/*all.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    install -m 0644 ${S}/*mipsel.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    install -m 0644 ${S}/*mips32el.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-et-* || true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-multiquickbutton*.ipk || true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-backupsuite*.ipk || true
    install -m 0644 ${S}/enigma2-plugin-extensions-et-portal*.ipk ${DEPLOY_DIR_IPK}/3rdparty || true
    Z1=$(ls -1 ${S}/*${MACHINE}.ipk | wc -l)
    if [ $Z1 -gt 0 ]; then
        install -m 0644 ${S}/*${MACHINE}.ipk ${DEPLOY_DIR_IPK}/${MACHINE} #|| true
    fi 
    pkgdir=${DEPLOY_DIR_IPK}/3rdparty
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
    pkgdir=${DEPLOY_DIR_IPK}/${MACHINE}_3rdparty
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
}

do_deploy_append_sh4() {
    install -d 0755 ${DEPLOY_DIR_IPK}/3rdparty
    install -d 0755 ${DEPLOY_DIR_IPK}/${MACHINE}_3rdparty
    install -m 0644 ${S}/*all.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    install -m 0644 ${S}/*sh4.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-et-* || true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-hbbtv_* || true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-multiquickbutton*.ipk || true
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-backupsuite*.ipk || true
    install -m 0644 ${S}/enigma2-plugin-extensions-et-portal*.ipk ${DEPLOY_DIR_IPK}/3rdparty || true
    Z1=$(ls -1 ${S}/*${MACHINE}.ipk | wc -l)
    if [ $Z1 -gt 0 ]; then
        install -m 0644 ${S}/*${MACHINE}.ipk ${DEPLOY_DIR_IPK}/${MACHINE} #|| true
    fi    
    pkgdir=${DEPLOY_DIR_IPK}/3rdparty
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
    pkgdir=${DEPLOY_DIR_IPK}/${MACHINE}_3rdparty
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
}

do_deploy_append_vusolo4k() {
    install -d 0755 ${DEPLOY_DIR_IPK}/3rdparty
    install -d 0755 ${DEPLOY_DIR_IPK}/${MACHINE}_3rdparty
    install -m 0644 ${S}/*all.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    Z2=$(ls -1 ${S}/*$vusolo4k.ipk | wc -l)
    if [ $Z2 -gt 0 ]; then
        install -m 0644 ${S}/*vusolo4k.ipk ${DEPLOY_DIR_IPK}/3rdparty #|| true
    fi 
    rm -f ${DEPLOY_DIR_IPK}/3rdparty/enigma2-plugin-extensions-backupsuite*.ipk || true
    install -m 0644 ${S}/enigma2-plugin-extensions-et-portal*.ipk ${DEPLOY_DIR_IPK}/3rdparty || true
    Z1=$(ls -1 ${S}/*${MACHINE}.ipk | wc -l)
    if [ $Z1 -gt 0 ]; then
        install -m 0644 ${S}/*${MACHINE}.ipk ${DEPLOY_DIR_IPK}/${MACHINE} #|| true
    fi    
    pkgdir=${DEPLOY_DIR_IPK}/3rdparty
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
    pkgdir=${DEPLOY_DIR_IPK}/${MACHINE}_3rdparty
    if [ -e $pkgdir ]; then
        chmod 0755 $pkgdir
    fi
}

addtask do_deploy before do_package_write after do_package_write_ipk 
