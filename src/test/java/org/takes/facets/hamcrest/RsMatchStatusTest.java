/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
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
package org.takes.facets.hamcrest;

import java.io.IOException;
import java.net.HttpURLConnection;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.rq.RqFake;
import org.takes.tk.TkEmpty;
import org.takes.tk.TkHTML;

/**
 * Test case for {@link RsMatchStatus}.
 * @author Erim Erturk (erimerturk@gmail.com)
 * @version $Id$
 * @since 0.13
 */
public final class RsMatchStatusTest {

    /**
     * Happy path tester.
     * Should test response status code equal to expected code.
     * @throws IOException If some problem inside
     */
    @Test
    public void responseCodeIsEqualToExpected() throws IOException {
        MatcherAssert.assertThat(
            new TkHTML("<html></html>").act(new RqFake()),
            Matchers.is(new RsMatchStatus(HttpURLConnection.HTTP_OK))
        );
        MatcherAssert.assertThat(
            new TkEmpty().act(new RqFake()),
            Matchers.is(new RsMatchStatus(HttpURLConnection.HTTP_OK))
        );
    }

    /**
     * Fail path tester.
     * Should test expected code not equal case
     * @throws IOException If some problem inside
     */
    @Test
    public void responseCodeIsNotEqualToExpected() throws IOException {
        MatcherAssert.assertThat(
            new TkHTML("<html><body/></html>").act(new RqFake()),
            Matchers.not(
                Matchers.is(
                    new RsMatchStatus(HttpURLConnection.HTTP_NOT_FOUND)
                )
            )
        );
    }

}