DESCRIPTION = "OpenNFR base files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/usr/bin/* /usr/lib/python2.7/* /usr/lib/python2.7/site-packages/twisted/web/*"
S = "${WORKDIR}"

do_install() {
    install -d ${D}/usr/lib/python2.7
    for f in argparse.py
    do
        install -m 755 ${f} ${D}/usr/lib/python2.7/${f}
    done

    install -d ${D}/usr/lib/python2.7/site-packages/twisted/web
    for f in client.py
    do
        install -m 755 ${f} ${D}/usr/lib/python2.7/site-packages/twisted/web/${f}
    done

    install -d ${D}/media
    mkdir -p ${D}/media/card
    mkdir -p ${D}/media/cf
    mkdir -p ${D}/media/hdd
    mkdir -p ${D}/media/net
    mkdir -p ${D}/media/upnp
    mkdir -p ${D}/media/usb
    mkdir -p ${D}/media/usb1
    mkdir -p ${D}/media/usb2
    mkdir -p ${D}/media/usb3


    cd ${D}/usr/lib
    ln -s libbz2.so.0.0.0 libbz2.so.1.0 || true
}
