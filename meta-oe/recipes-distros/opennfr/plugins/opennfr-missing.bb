DESCRIPTION = "OpenNFR missing files"
LICENSE = "GPL2"

require conf/license/license-gplv2.inc

PACKAGE_ARCH := "${MACHINE_ARCH}"

SRCREV = "${AUTOREV}"

SRC_URI = "file://*"

FILES_${PN} = "/usr/lib/python2.7/* /usr/lib/python2.7/site-packages/twisted/web/*"

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

}


