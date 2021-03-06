/* 
 * The IzPack Launcher
 * http://izpack.org/
 * http://izpack.codehaus.org/
 * 
 * Copyright (c) 2004 - 2008 Julien Ponge - All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

#ifndef LAUNCHER_H
#define LAUNCHER_H

#include <QString>
#include <QSettings>
#include <QProcess>
#include <QFile>
#include <QStringList>

class Launcher
{

private:

    QString javaExecPath;
    
    QString jar;
    
    QString jre;
    
    QString download;

public:

    Launcher();
        
    bool detectJRE();
    
    bool launch();
    
    bool launch(const QString &runtimeExecPath);
    
    void downloadJRE();
    
    bool installProvidedJRE();
    
    inline QString getJREPath() const
    {
        return javaExecPath;
    }
    
    inline bool isJREProvided() const
    {
        return jre != "";
    }

    int execute(const QString &program, const QStringList &arguments);

    int execute(const QString &program);
};

#endif
