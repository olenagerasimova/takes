/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.facets.fork.am;

/**
 * Matches specified version.
 *
 * @author Valeriy Zhirnov (neonailol@gmail.com)
 * @version $Id$
 * @since 1.7.2
 */
public final class AmVersion implements AgentMatch {

    /**
     * User agent name.
     */
    private final String agent;

    /**
     * Version matcher.
     */
    private final VersionMatch version;

    /**
     * Ctor.
     * @param agent Uer agent name.
     * @param version Version matcher.
     */
    public AmVersion(final String agent, final VersionMatch version) {
        this.agent = agent;
        this.version = version;
    }

    @Override
    @SuppressWarnings("PMD.ConfusingTernary")
    public boolean matches(final String token) {
        boolean result = true;
        final String[] parts = token.split("/");
        if (parts.length <= 1) {
            result = false;
        } else if (!this.agent.equalsIgnoreCase(parts[0])) {
            result = false;
        } else if (!this.version.matches(extractMajorVersion(parts[1]))) {
            result = false;
        }
        return result;
    }

    /**
     * Parse major version number.
     * @param part Token part.
     * @return Parsed major version number.
     */
    private static int extractMajorVersion(final String part) {
        final String[] parts = part.split("\\.");
        return Integer.parseInt(parts[0]);
    }

    /**
     * Matches specified version when it greater than specified one.
     *
     * @author Valeriy Zhirnov (neonailol@gmail.com)
     * @version $Id$
     * @since 1.7.2
     */
    public static final class VmGt implements VersionMatch {

        /**
         * Version.
         */
        private final int ver;

        /**
         * Ctor.
         * @param ver Version.
         */
        public VmGt(final int ver) {
            this.ver = ver;
        }

        @Override
        public boolean matches(final int version) {
            return version > this.ver;
        }
    }
}
