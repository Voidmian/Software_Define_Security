const express = require('express')
const app = express()
const port = 8000
const path = require('path')
const multer = require('multer')
const fs = require('fs')
const cors = require('cors');
app.use(cors());
// 文件上传
const upload = multer({
  storage: multer.diskStorage({
    destination(req, file, cb) {
      // 上传文件存在 public/uploads 下
      cb(null, 'public/uploads')
    },
    filename(req, file, cb) {
      // 使用时间戳作为上传的文件名
      const extname = path.extname(file.originalname)
      cb(null, Date.now() + extname)
    }
  })
})

// 静态资源
app.use('/public', express.static(path.join(__dirname, 'public')))

// // 处理所有的 OPTIONS 预检请求
// app.options('*', (req, res, next) => {
//   res.setHeader('Access-Control-Allow-Origin', 'http://localhost:3000')
//   // 追加允许的请求方法
//   res.setHeader('Access-Control-Allow-Methods', 'POST, DELETE')
//   // 客户端发了JSON 追加允许的请求头 Content-Type
//   res.setHeader('Access-Control-Allow-Headers', 'x-requested-with,Content-Type')
//   next()
// })

// // 跨域中间件
// const cors = (req, res, next) => {
//   res.setHeader('Access-Control-Allow-Origin', 'http://localhost:3000')
//   next()
// }

// 使用前面定义好的 cors 中间件和 upload 中间件
app.post('/upload', upload.single('file'), (req, res) => {
  const { file: { filename, path } } = req
  console.log("file", req.file);
  res.json({
    ok: true,
    message: '图片上传成功',
    data: {
      name: filename,
      url: path
    }
  })
})

// 解析客户端请求的JSON数据
app.use(express.json())

// 删除文件
app.delete('/delete', cors, async (req, res, next) => {
  // const { path } = req.body
  const path = '11'
  fs.unlink(path, (err) => {
    if (err) return next(err)
    res.json({
      ok: true,
      message: '删除图片成功'
    })
  })
})

// 处理前面发生的错误
// app.use((err, req, res, next) => {
//   console.log(err + '')
//   res.json({
//     ok: false,
//     message: '服务器错误'
//   })
// })

app.listen(port, () => {
  console.log(`http://localhost:${port}`)
})