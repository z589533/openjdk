/*
 * Copyright (c) 1996, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package sun.security.ssl;

/**
 * DTLS record
 */
interface DTLSRecord extends Record {

    static final int    headerSize = 13;            // DTLS record header

    static final int    handshakeHeaderSize = 12;   // DTLS handshake header

    /*
     * The size of the header plus the max IV length
     */
    static final int    headerPlusMaxIVSize =
                                      headerSize        // header
                                    + maxIVLength;      // iv

    /*
     * The maximum size that may be increased when translating plaintext to
     * ciphertext fragment.
     */
    static final int    maxPlaintextPlusSize =
                                      headerSize        // header
                                    + maxIVLength       // iv
                                    + maxMacSize        // MAC or AEAD tag
                                    + maxPadding;       // block cipher padding

    /*
     * the maximum record size
     */
    static final int    maxRecordSize =
                                      headerPlusMaxIVSize   // header + iv
                                    + maxDataSize           // data
                                    + maxPadding            // padding
                                    + maxMacSize;           // MAC or AEAD tag

    /*
     * For CBC protection in SSL3/TLS1, we break some plaintext into two
     * packets.  Max application data size for the second packet.
     */
    static final int    maxDataSizeMinusOneByteRecord =
                                  maxDataSize       // max data size
                                - (                 // max one byte record size
                                      headerPlusMaxIVSize   // header + iv
                                    + 1             // one byte data
                                    + maxPadding    // padding
                                    + maxMacSize    // MAC
                                  );

    /*
     * Maximum record size for alert and change cipher spec records.
     * They only contain 2 and 1 bytes of data, respectively.
     * Allocate a smaller array.
     */
    static final int    maxAlertRecordSize =
                                      headerPlusMaxIVSize   // header + iv
                                    + 2                     // alert
                                    + maxPadding            // padding
                                    + maxMacSize;           // MAC

}
